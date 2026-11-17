module order.app {
    requires order.domain;
    requires order.storage;
    requires order.processing;
    requires order.concurrent;

    requires javafaker;
}
