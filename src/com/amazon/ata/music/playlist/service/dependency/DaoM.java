package com.amazon.ata.music.playlist.service.dependency;

import com.amazon.ata.music.playlist.service.dynamodb.AlbumTrackDao;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DaoM {
    public DaoM() {
    }

    @Provides
    @Singleton
    public PlaylistDao providePlaylistDao(DynamoDBMapper provideDynamoDBMapper) {
        return new PlaylistDao(provideDynamoDBMapper);
    }

    @Provides
    @Singleton
    public AlbumTrackDao provideAlbumTrackDao(DynamoDBMapper provideDynamoDBMapper) {
        return new AlbumTrackDao(provideDynamoDBMapper);
    }
}
