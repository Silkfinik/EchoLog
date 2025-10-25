package com.silkfinik.data.repository

import com.silkfinik.data.datasource.local.db.EchoEntity
import com.silkfinik.domain.model.Echo

internal inline fun EchoEntity.toDomain(): Echo = Echo(
    id = this.id,
    title = this.title,
    text = this.text,
    latitude = this.latitude,
    longitude = this.longitude,
    timestamp = this.timestamp,
    userId = this.userId
)

internal inline fun Echo.toEntity(isSynced: Boolean = false): EchoEntity = EchoEntity(
    id = this.id,
    title = this.title,
    text = this.text,
    latitude = this.latitude,
    longitude = this.longitude,
    timestamp = this.timestamp,
    isSynced = isSynced,
    userId = this.userId
)