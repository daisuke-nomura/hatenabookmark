package com.kyaracter.hatenabookmark

import com.kyaracter.hatenabookmark.data.remote.entity.Rss
import org.junit.Assert
import org.junit.Test
import org.simpleframework.xml.core.Persister


class XmlParseTest {

    private val serial = Persister()

    private fun readFile(name: String): String {
        return javaClass.classLoader.getResourceAsStream(name).bufferedReader().use {
            it.readText()
        }
    }

    private fun readXml(xml: String): Rss {
        return serial.read(Rss::class.java, xml)
    }

    @Test
    fun parse1() {
        val file = readFile("hotentry.rss")
        val xml = readXml(file)
        Assert.assertNotNull(xml)
    }

    @Test
    fun parse2() {
        val file = readFile("social.rss")
        val xml = readXml(file)
        Assert.assertNotNull(xml)
    }

    @Test
    fun parse3() {
        val file = readFile("economics.rss")
        val xml = readXml(file)
        Assert.assertNotNull(xml)
    }

    @Test
    fun parse4() {
        val file = readFile("life.rss")
        val xml = readXml(file)
        Assert.assertNotNull(xml)
    }
}
