package com.kyaracter.hatenabookmark.data.remote.entity

import org.simpleframework.xml.*

@Root(strict = false)
class Rss {
    @JvmField
    @field:ElementList(inline = true, name = "item")
    var items: List<Item>? = null
}