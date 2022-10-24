package com.amazon.ata.music.playlist.service.dependency;

import dagger.internal.Preconditions;

public final class daggerserviceComponent implements ServiceComponent {
    private daggerserviceComponent(Builder builder) {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ServiceComponent create() {
        return (new Builder()).build();
    }

    public static final class Builder {
        private Builder() {
        }

        public ServiceComponent build() {
            return new daggerserviceComponent(this);
        }

        /** @deprecated */
        @Deprecated
        public Builder mapperModule(MapperModule mapperModule) {
            Preconditions.checkNotNull(mapperModule);
            return this;
        }

        /** @deprecated */
        @Deprecated
        public Builder daoModule(DaoModule daoModule) {
            Preconditions.checkNotNull(daoModule);
            return this;
        }
    }
}
