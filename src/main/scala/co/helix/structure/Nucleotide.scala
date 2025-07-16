package co.helix.structure

sealed trait Nucleotide
object Nucleotide {
    case object A extends Nucleotide
    case object C extends Nucleotide
    case object G extends Nucleotide
    case object T extends Nucleotide

    val all: List[Nucleotide] = List(A, C, G, T)

    def fromChar(c: Char): Option[Nucleotide] =
        c.toUpper match {
            case 'A' => Some(A)
            case 'C' => Some(C)
            case 'G' => Some(G)
            case 'T' => Some(T)
            case _   => None
        }

    def toChar(n: Nucleotide): Char = n match {
        case A => 'A'
        case C => 'C'
        case G => 'G'
        case T => 'T'
    }
}
