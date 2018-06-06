package fr.meteordesign.data.entity.mapper

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import fr.meteordesign.data.entity.TrackEntity
import fr.meteordesign.domain.Track

fun transform(trackEntities: LiveData<List<TrackEntity>>): LiveData<List<Track>> =
        Transformations.map(trackEntities,
                { it.map { trackEntity -> transform(trackEntity) } })

fun transform(trackEntity: TrackEntity): Track = Track(trackEntity.id, trackEntity.title)

