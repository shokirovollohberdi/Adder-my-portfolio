package uz.shokirov.addermyporflios.model

class Skills {
    var skill: String? = null
    var percent: Int? = null


    constructor()
    constructor(skill: String?, percent: Int?) {
        this.skill = skill
        this.percent = percent
    }


}