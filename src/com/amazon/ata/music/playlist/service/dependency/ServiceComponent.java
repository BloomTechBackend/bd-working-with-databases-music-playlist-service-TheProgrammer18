package com.amazon.ata.music.playlist.service.dependency;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component (modules = {MapperModule.class, DaoModule.class})
public interface ServiceComponent {
}
