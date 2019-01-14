package com.kyaracter.hatenabookmark.data.remote.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(strict = false)
class Item {

    @JvmField
    @field:Element(name = "title")
    var title: String? = null

    @JvmField
    @field:Element(name = "link")
    var link: String? = null

    @JvmField
    @field:Element(name = "description", required = false)
    var description: String? = null

    @JvmField
    @field:Element(name = "date", required = false)
    @Namespace(reference = "http://purl.org/dc/elements/1.1/")
    var date: String? = null

    @JvmField
    @field:Element(name = "imageurl", required = false)
    @Namespace(reference = "http://www.hatena.ne.jp/info/xmlns#")
    var thumbnail: String? = null

    @JvmField
    @field:Element(name = "bookmarkcount", required = false)
    @Namespace(reference = "http://www.hatena.ne.jp/info/xmlns#")
    var bookmarkCount: Int = 0
}