package map

data class Point(val x: Int, val y: Int) {
    override fun toString(): String {
        return "( $x, $y )"
    }
}