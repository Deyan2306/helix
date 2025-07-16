package co.helix

import co.helix.structure.{DNA, Helix, Nucleotide, RNA}

object PrettyPrinter {

    // ANSI Colors
    private val RESET = "\u001B[0m"
    private val GREEN = "\u001B[32m"
    private val RED = "\u001B[31m"
    private val BLUE = "\u001B[34m"

    private def color(n: Char): String = n match {
        case 'A' => Console.RED + n + Console.RESET
        case 'C' => Console.BLUE + n + Console.RESET
        case 'G' => Console.GREEN + n + Console.RESET
        case 'T' => Console.YELLOW + n + Console.RESET
        case 'U' => Console.MAGENTA + n + Console.RESET
        case _   => n.toString
    }

    private val nucleotideNames: Map[Nucleotide, String] = Map(
        Nucleotide.A -> "Adenine",
        Nucleotide.C -> "Cytosine",
        Nucleotide.G -> "Guanine",
        Nucleotide.T -> "Thymine"
    )

    def printValidation(dnaResult: Either[String, DNA]): Unit = dnaResult match {
        case Right(dna) =>
            println(s"$GREEN✅ Valid DNA:$RESET ${DNA.toString(dna)}")
        case Left(error) =>
            println(s"$RED❌ Invalid DNA:$RESET $error")
    }

    def printCounts(counts: Map[Nucleotide, Int]): Unit = {
        println(s"\n$BLUE Nucleotide Counts:$RESET")
        println("┌────────────┬───────┐")
        println("│ Name       │ Count │")
        println("├────────────┼───────┤")
        for ((nucleotide, count) <- counts) {
            val name = nucleotideNames(nucleotide)
            println(f"│ ${name.padTo(10, ' ')} │ ${count}%5d │")
        }
        println("└────────────┴───────┘")
    }

    def printRandomDNA(dna: DNA): Unit = {
        println(s"\n🔹 Random DNA (length: ${dna.sequence.length}):")
        println(s"5' ${DNA.toString(dna).map(c => color(c)).mkString(" ")} 3'")
    }

    def showTranscription(dna: DNA, rna: RNA): Unit = {
        println("\n🧬 DNA → RNA Transcription:")
        println(" DNA: 5' " + dna.sequence.map(n => color(n.char)).mkString(" ") + " 3'")
        println("       " + dna.sequence.map(_ => "|").mkString(" "))
        println(" RNA: 3' " + rna.sequence.map(c => color(c)).mkString(" ") + " 5'")
    }

    def showReverseComplement(original: DNA, reverse: DNA): Unit = {
        println("\n🔄 Reverse Complement:")
        println(" Original:   5' " + original.sequence.map(n => color(n.char)).mkString(" ") + " 3'")
        println(" Complement: 3' " + original.sequence.map(n => color(n.complement.char)).mkString(" ") + " 5'")
        println(" Reverse:    5' " + reverse.sequence.map(n => color(n.char)).mkString(" ") + " 3'")
    }

    def showGCContent(dna: DNA): Unit = {
        val gcCount = dna.sequence.count(n => n == Nucleotide.G || n == Nucleotide.C)
        val total = dna.sequence.length
        val gcPercent = (gcCount.toDouble / total) * 100

        println("\n🌱 GC Content Analysis:")
        println(s" Sequence (5' → 3'): ${dna.sequence.map(n => color(n.char)).mkString(" ")}")
        println(s"\n Total Length: $total")
        println(s" G + C Count: $gcCount")
        println(f" GC Content: ${gcPercent}%.2f%%")
        
        val barLength = 30
        val gcBars = ((gcPercent / 100) * barLength).toInt
        val atBars = barLength - gcBars
        val bar = Console.GREEN + "█" * gcBars + Console.RED + "█" * atBars + Console.RESET

        println(s"\n GC Content Visualization:")
        println(s" [$bar] ${f"$gcPercent%.2f"}%")
        println(s" ${Console.GREEN}█${Console.RESET} = GC, ${Console.RED}█${Console.RESET} = AT\n")

        if (gcPercent < 40) {
            println(s"${Console.RED}⚠ Low GC Content: May indicate AT-rich region.${Console.RESET}")
        } else if (gcPercent > 60) {
            println(s"${Console.GREEN}✅ High GC Content: Stable DNA region.${Console.RESET}")
        } else {
            println(s"${Console.YELLOW}ℹ Moderate GC Content: Balanced composition.${Console.RESET}")
        }
    }

    def showGCContentSubsections(dna: DNA, k: Int): Unit = {
        val subsections = Helix.gcContentSubsections(dna, k)

        println(s"\n📊 GC Content per $k-base Segment:")
        println("┌───────────────┬────────────┐")
        println("│ Segment Index │ GC Content │")
        println("├───────────────┼────────────┤")

        for ((gc, idx) <- subsections.zipWithIndex) {
            val color = if (gc > 60) Console.GREEN else if (gc < 40) Console.RED else Console.YELLOW
            println(f"│ ${idx}%13d │ $color${gc}%9.2f%%${Console.RESET} │")
        }

        println("└───────────────┴────────────┘")

        println("\n GC Content Visualization:")
        subsections.zipWithIndex.foreach { case (gc, idx) =>
            val bar = "█" * (gc / 5).toInt
            println(f"Segment $idx%2d: $bar $gc%.2f%%")
        }
    }

}
