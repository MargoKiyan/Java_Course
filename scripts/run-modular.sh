#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."

mvn -q clean package

# Всі міжмодульні JAR і транзитивні залежності JavaFaker збираються Maven
# у order-app/target/dependency (завдяки dependency-plugin у order-app/pom.xml).
# На модульний шлях додаємо сам order.app (з Main.class) і всі JAR залежностей.
java --module-path "order-app/target/classes:order-app/target/dependency" \
     --add-modules ALL-MODULE-PATH,java.sql \
     --module order.app/com.kyian.orders.app.Main
