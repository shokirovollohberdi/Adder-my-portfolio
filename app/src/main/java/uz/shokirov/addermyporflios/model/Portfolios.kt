package uz.shokirov.addermyporflios.model

class Portfolios {
    var name: String? = null
    var imageUrl: String? = null
    var size: String? = null
    var description: String? = null
    var screenShotUrl: String? = null
    var apkUrl: String? = null


    constructor()
    constructor(
        name: String?,
        imageUrl: String?,
        size: String?,
        description: String?,
        screenShotUrl: String?,
        apkUrl: String?
    ) {
        this.name = name
        this.imageUrl = imageUrl
        this.size = size
        this.description = description
        this.screenShotUrl = screenShotUrl
        this.apkUrl = apkUrl
    }

}