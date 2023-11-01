package kedaiapps.projeku.testandroidsalt.utils.downloadImage

interface Downloader {
    fun downloadFile(url: String): Long
}