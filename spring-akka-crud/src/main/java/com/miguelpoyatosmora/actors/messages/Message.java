package com.miguelpoyatosmora.actors.messages;

import java.io.Serializable;

public final class Message {

    static public class Get implements Serializable, HasKey {
        private static final long serialVersionUID = 1L;
        private final String key;

        public Get(String key) {
            this.key = key;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "Get{" +
                    "key='" + key + '\'' +
                    '}';
        }
    }

    static public final class Entry implements Serializable, HasKey {
        private static final long serialVersionUID = 1L;
        private final String key;
        private final String value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    static public final class Evict implements Serializable, HasKey {
        private static final long serialVersionUID = 1L;
        private final String key;

        public Evict(String key) {
            this.key = key;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "Evict{" +
                    "key='" + key + '\'' +
                    '}';
        }
    }
}