package com.prafullkumar.domeupdates.data.mappers

import com.prafullkumar.domeupdates.data.local.posts.PostEntity
import com.prafullkumar.domeupdates.domain.model.Post

fun PostEntity.toPost(): Post {
    return Post(
        id = id,
        username = username,
        title = title,
        body = body,
        timestamp = timestamp,
        numberOfComments = numberOfComments,
        numberOfShares = numberOfShares,
    )
}

fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        username = username,
        title = title,
        body = body,
        timestamp = timestamp,
        numberOfComments = numberOfComments,
        numberOfShares = numberOfShares,
    )
}