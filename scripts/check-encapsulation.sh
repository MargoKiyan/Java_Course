#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
mvn -q -DskipTests package

STORAGE_JAR="order-storage/target/order-storage-1.0-SNAPSHOT.jar"
DOMAIN_JAR="order-domain/target/order-domain-1.0-SNAPSHOT.jar"
MODULE_PATH="$STORAGE_JAR:$DOMAIN_JAR"

echo '--- JPMS descriptor order.storage ---'
jar --describe-module --file "$STORAGE_JAR"
if jar --describe-module --file "$STORAGE_JAR" | grep -q 'exports com.kyian.orders.storage.internal'; then
    echo 'ПОМИЛКА: внутрішній пакет order.storage.internal експортовано'
    exit 1
fi

TMP="$(mktemp -d)"
trap 'rm -rf "$TMP"' EXIT
cat > "$TMP/Allowed.java" <<'JAVA'
import com.kyian.orders.storage.OrderRepository;
public class Allowed { OrderRepository repository; }
JAVA
cat > "$TMP/Unauthorized.java" <<'JAVA'
import com.kyian.orders.storage.internal.ConcurrentOrderRepository;
public class Unauthorized { ConcurrentOrderRepository repository; }
JAVA

# Контрольна позитивна проба: публічний API має компілюватися.
javac --module-path "$MODULE_PATH" --add-modules order.storage -d "$TMP" "$TMP/Allowed.java"
echo 'OK: експортований API order.storage доступний.'

# Негативна проба: компілятор мусить відмовити саме через відсутній exports.
if javac --module-path "$MODULE_PATH" --add-modules order.storage -d "$TMP"          "$TMP/Unauthorized.java" > "$TMP/error.log" 2>&1; then
    echo 'ПОМИЛКА: сторонній код отримав доступ до внутрішнього класу!'
    exit 1
fi
if ! grep -Eiq 'does not export|not visible|not accessible|не експорту|недоступн' "$TMP/error.log"; then
    echo 'ПОМИЛКА: javac завершився помилкою, але не через заборону доступу JPMS:'
    cat "$TMP/error.log"
    exit 1
fi
echo 'OK: JPMS заборонив імпорт order.storage.internal (очікувана відмова).'
cat "$TMP/error.log"
