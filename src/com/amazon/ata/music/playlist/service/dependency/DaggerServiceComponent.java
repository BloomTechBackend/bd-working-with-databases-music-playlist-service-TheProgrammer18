package com.amazon.ata.music.playlist.service.dependency;

import com.amazon.ata.music.playlist.service.activity.*;
import com.amazon.ata.music.playlist.service.dynamodb.AlbumTrackDao;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;

import javax.inject.Provider;

public final class DaggerServiceComponent implements ServiceComponent {
    private Provider<DynamoDBMapper> provideDynamoDBMapperProvider;

    private Provider<PlaylistDao> providePlaylistDaoProvider;

    private Provider<AlbumTrackDao> provideAlbumTrackDaoProvider;

    private DaggerServiceComponent(DaggerServiceComponent.Builder builder) {
        initialize(builder);
    }

    public static DaggerServiceComponent.Builder builder() {
        return new DaggerServiceComponent.Builder();
    }

    public static ServiceComponent create() {
        return new DaggerServiceComponent.Builder().build();
    }

    @SuppressWarnings("unchecked")
    private void initialize(final DaggerServiceComponent.Builder builder) {
        this.provideDynamoDBMapperProvider =
                DoubleCheck.provider(
                        MapperModule_ProvideDynamoDBMapperFactory.create(builder.mapperModule));
        this.providePlaylistDaoProvider =
                DoubleCheck.provider(
                        DaoM_ProvidePlaylistDaoFactory.create(builder.daoM, provideDynamoDBMapperProvider));
        this.provideAlbumTrackDaoProvider =
                DoubleCheck.provider(
                        DaoM_ProvideAlbumTrackDaoFactory.create(builder.daoM, provideDynamoDBMapperProvider));
    }

    @Override
    public CreatePlaylistActivity provideCreatePlaylistActivity() {
        return new CreatePlaylistActivity(providePlaylistDaoProvider.get());
    }

    @Override
    public GetPlaylistActivity provideGetPlaylistActivity() {
        return new GetPlaylistActivity(providePlaylistDaoProvider.get());
    }

    @Override
    public UpdatePlaylistActivity provideUpdatePlaylistActivity() {
        return new UpdatePlaylistActivity(providePlaylistDaoProvider.get());
    }

    @Override
    public AddSongToPlaylistActivity provideAddSongToPlaylistActivity() {
        return new AddSongToPlaylistActivity(
                providePlaylistDaoProvider.get(), provideAlbumTrackDaoProvider.get());
    }

    @Override
    public GetPlaylistSongsActivity provideGetPlaylistSongsActivity() {
        return new GetPlaylistSongsActivity(providePlaylistDaoProvider.get());
    }

    @Override
    public MapperModule provideMapperModule() {
        return null;
    }

    @Override
    public DaoM provideDaoM() {
        return null;
    }

    public static final class Builder {
        private MapperModule mapperModule;

        private DaoM daoM;

        private Builder() {}

        public ServiceComponent build() {
            if (mapperModule == null) {
                this.mapperModule = new MapperModule();
            }
            if (daoM == null) {
                this.daoM = new DaoM();
            }
            return new DaggerServiceComponent(this);
        }

        public DaggerServiceComponent.Builder mapperModule(MapperModule mapperModule) {
            this.mapperModule = Preconditions.checkNotNull(mapperModule);
            return this;
        }

        public DaggerServiceComponent.Builder daoM(DaoM daoM) {
            this.daoM = Preconditions.checkNotNull(daoM);
            return this;
        }
    }
}
