package com.challenge.drivers;

public enum Browser {
    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new ChromeDriverFactory();
        }
    };
    public abstract AbstractDriver getDriverFactory();
}
