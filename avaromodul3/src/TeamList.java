public class TeamList {
    Team head;
    Team tail;

    public TeamList() {
        this.head = null;
        this.tail = null;
    }

    // Untuk menambahkan tim
    public void addTeam(String teamName, int gamesPlayed, int wins, int losses,
                       double totalPoints, double totalAssists, double totalRebounds) {
        Team newTeam = new Team(teamName, gamesPlayed, wins, losses, totalPoints, totalAssists, totalRebounds);
        if (this.head == null) {
            this.head = newTeam;
            this.tail = newTeam;
        } else {
            newTeam.prev = this.tail;
            this.tail.next = newTeam;
            this.tail = newTeam;
        }
    }

    // Perhitungan rata-rata
    public void calculateTeamAverages() {
        Team current = this.head;
        while (current != null) {
            current.ppg = roundToTwoDecimals(current.totalPoints / current.gamesPlayed);
            current.apg = roundToTwoDecimals(current.totalAssists / current.gamesPlayed);
            current.rpg = roundToTwoDecimals(current.totalRebounds / current.gamesPlayed);
            current = current.next;
        }
    }
	// Ini buat ngebulatin maksudnya
    public void calculateAllPlayerAverages() {
        Team current = this.head;
        while (current != null) {
            current.players.calculateAverages();
            current = current.next;
        }
    }

    private double roundToTwoDecimals(double value) {
        return ((int)(value * 100 + 0.5)) / 100.0;
    }

    //     // Method Tampilan
    public void displayTeams() {
        if (this.head == null) {
            System.out.println("Daftar tim kosong.");
            return;
        }

        System.out.println("======================================================================");
        System.out.printf("%-25s | %-2s | %-3s | %-3s | %-6s | %-5s | %-5s%n", 
                         "TEAM NAME", "GP", "W", "L", "PPG", "APG", "RPG");
        System.out.println("----------------------------------------------------------------------");
        Team current = this.head;
        while (current != null) {
            System.out.printf("%-25s | %-2d | %-3d | %-3d | %-6.2f | %-5.2f | %-5.2f%n", 
                            current.teamName, current.gamesPlayed, current.wins, current.losses, 
                            current.ppg, current.apg, current.rpg);
            current = current.next;
        }
        System.out.println("======================================================================");
    }

    public void displayTeamPlayers() {
        Team current = this.head;
        while (current != null) {
            System.out.println("\n" + current.teamName);
            current.players.display();
            current = current.next;
        }
    }

    public void displayAllTeamPlayers() {
        PlayerList allPlayers = getAllPlayers();
        allPlayers.displayWithHeader("-= Semua Pemain Digabungkan =-");
    }

    // Quick Sort untuk tim berdasarkan kemenangan (wins)
    public void quickSort() {
        if (this.head != null) {
            quickSort(this.head, this.tail);
        }
    }

    private void quickSort(Team low, Team high) {
        if (low != null && high != null && low != high && low != high.next) {
            Team pivot = partition(low, high);
            quickSort(low, pivot.prev);
            quickSort(pivot.next, high);
        }
    }

    private Team partition(Team low, Team high) {
        Team pivot = high;
        Team i = low.prev;

        for (Team j = low; j != high; j = j.next) { // Urutkan descending berdasarkan wins
            if (j.wins >= pivot.wins) {
                i = (i == null) ? low : i.next;
                swapData(i, j);
            }
        }
        i = (i == null) ? low : i.next;
        swapData(i, high);
        return i;
    }

// Swap semua data tim
    private void swapData(Team a, Team b) {
        String tempName = a.teamName;
        int tempGP = a.gamesPlayed;
        int tempWins = a.wins;
        int tempLosses = a.losses;
        double tempPoints = a.totalPoints;
        double tempAssists = a.totalAssists;
        double tempRebounds = a.totalRebounds;
        double tempPPG = a.ppg;
        double tempAPG = a.apg;
        double tempRPG = a.rpg;
        PlayerList tempPlayers = a.players;

        a.teamName = b.teamName;
        a.gamesPlayed = b.gamesPlayed;
        a.wins = b.wins;
        a.losses = b.losses;
        a.totalPoints = b.totalPoints;
        a.totalAssists = b.totalAssists;
        a.totalRebounds = b.totalRebounds;
        a.ppg = b.ppg;
        a.apg = b.apg;
        a.rpg = b.rpg;
        a.players = b.players;

        b.teamName = tempName;
        b.gamesPlayed = tempGP;
        b.wins = tempWins;
        b.losses = tempLosses;
        b.totalPoints = tempPoints;
        b.totalAssists = tempAssists;
        b.totalRebounds = tempRebounds;
        b.ppg = tempPPG;
        b.apg = tempAPG;
        b.rpg = tempRPG;
        b.players = tempPlayers;
    }

    public void sortAllTeamsPlayers() {
        Team current = this.head;
        while (current != null) {
            current.players.mergeSort();
            current = current.next;
        }
    }

    // Manajemen pemain

    public PlayerList getAllPlayers() {
        PlayerList allPlayers = new PlayerList();
        Team currentTeam = this.head;
        while (currentTeam != null) {
            Player currentPlayer = currentTeam.players.head;
            while (currentPlayer != null) {
                allPlayers.addPlayer(currentPlayer.name, currentPlayer.jerseyNumber,
                                   currentPlayer.totalPoints, currentPlayer.totalAssists,
                                   currentPlayer.totalRebounds, currentPlayer.gamesPlayed);
                currentPlayer = currentPlayer.next;
            }
            currentTeam = currentTeam.next;
        }
        return allPlayers;
    }

    public void mergeSortAllTeamPlayers() {
        PlayerList allPlayers = getAllPlayers();
        allPlayers.calculateAverages();
        allPlayers.mergeSort();
        allPlayers.displayWithHeader("-= Semua Pemain Diurutkan Berdasarkan PPG (Points Per Game) Menggunakan Merge Sort =-");
        
        // Tampilkan waktu eksekusi
        long startTime = System.nanoTime();
        allPlayers.mergeSort();
        long elapsedTime = System.nanoTime() - startTime;
        System.out.printf("Elapsed Time is %.4f msec\n", elapsedTime / 1_000_000.0);
    }

    public void quickSortAllTeamPlayers() {
        PlayerList allPlayers = getAllPlayers();
        allPlayers.calculateAverages();
        allPlayers.quickSort();
        allPlayers.displayWithHeader("-= Semua Pemain Diurutkan Berdasarkan PPG (Points Per Game) Menggunakan Quick Sort =-");
        
        // Tampilkan waktu eksekusi
        long startTime = System.nanoTime();
        allPlayers.quickSort();
        long elapsedTime = System.nanoTime() - startTime;
        System.out.printf("Elapsed Time is %.4f msec\n", elapsedTime / 1_000_000.0);
    }

    // ==================== SEARCH OPERATIONS ====================
    public void searchPlayerByPPGLinear(double ppg) {
        System.out.println("\nMencari Pemain dengan PPG " + ppg + " Menggunakan Linear Search");
        PlayerList allPlayers = getAllPlayers();
        allPlayers.calculateAverages();
        
        long startTime = System.nanoTime();
        Player found = allPlayers.linearSearch(ppg);
        long elapsedTime = System.nanoTime() - startTime;
        
        if (found != null) {
            System.out.println("Ditemukan Pemain: " + found.name);
        } else {
            System.out.println("Pemain dengan PPG " + ppg + " tidak ditemukan.");
        }
        System.out.printf("Elapsed Time is %.4f msec\n", elapsedTime / 1_000_000.0);
    }

    public void searchPlayerByPPGBinary(double ppg) {
        System.out.println("\nMencari Pemain dengan PPG " + ppg + " Menggunakan Binary Search");
        PlayerList allPlayers = getAllPlayers();
        allPlayers.calculateAverages();
        allPlayers.mergeSort(); // Data harus terurut untuk binary search
        
        long startTime = System.nanoTime();
        Player found = allPlayers.binarySearch(ppg);
        long elapsedTime = System.nanoTime() - startTime;
        
        if (found != null) {
            System.out.println("Ditemukan Pemain: " + found.name);
        } else {
            System.out.println("Pemain dengan PPG " + ppg + " tidak ditemukan.");
        }
        System.out.printf("Elapsed Time is %.4f msec\n", elapsedTime / 1_000_000.0);
    }

    public void analyzeComplexity() {
        System.out.println("\n======================================================================");
        System.out.println("                      ANALISIS KOMPLEKSITAS");
        System.out.println("======================================================================");

        PlayerList allPlayers = getAllPlayers();
        allPlayers.calculateAverages();

        System.out.println("PERBANDINGAN SORTING:");
        System.out.println("----------------------------------------------------------------------");

        // Merge Sort Analysis
        PlayerList mergePlayers = allPlayers.copy();
        long startTimeMS = System.nanoTime();
        mergePlayers.mergeSort();
        long endTimeMS = System.nanoTime();
        double timeMS = (endTimeMS - startTimeMS) / 1_000_000.0;

        System.out.println("Merge Sort:");
        System.out.printf("  - Waktu eksekusi: %.4f msec\n", timeMS);
        System.out.printf("  - Recursive Call: %d\n", mergePlayers.getRecursiveCallCount());
        System.out.printf("  - Perbandingan: %d\n", mergePlayers.getComparisonCount());
        System.out.printf("  - Pertukaran: Tidak dihitung (merge tidak swap)\n");

        // Quick Sort Analysis
        PlayerList quickPlayers = allPlayers.copy();
        long startTimeQS = System.nanoTime();
        quickPlayers.quickSort();
        long endTimeQS = System.nanoTime();
        double timeQS = (endTimeQS - startTimeQS) / 1_000_000.0;

        System.out.println("\nQuick Sort:");
        System.out.printf("  - Waktu eksekusi: %.4f msec\n", timeQS);
        System.out.printf("  - Recursive Call: %d\n", quickPlayers.getRecursiveCallCount());
        System.out.printf("  - Perbandingan: %d\n", quickPlayers.getComparisonCount());
        System.out.printf("  - Pertukaran: %d\n", quickPlayers.getSwapCount());

        System.out.println("\nPERBANDINGAN SEARCHING:");
        System.out.println("----------------------------------------------------------------------");

        // Linear Search Analysis
        PlayerList linearPlayers = allPlayers.copy();
        double targetPPG = findRealPPG(linearPlayers);
        linearPlayers.resetCounters();
        long startTimeLS = System.nanoTime();
        linearPlayers.linearSearch(targetPPG);
        long endTimeLS = System.nanoTime();
        double timeLS = (endTimeLS - startTimeLS) / 1_000_000.0;

        System.out.println("Linear Search (pada data acak):");
        System.out.printf("  - Waktu eksekusi: %.4f msec\n", timeLS);
        System.out.printf("  - Perbandingan: %d\n", linearPlayers.getComparisonCount());

        // Binary Search Analysis
        PlayerList binaryPlayers = allPlayers.copy();
        binaryPlayers.resetCounters();
        long startTimeBS = System.nanoTime();
        binaryPlayers.mergeSort();
        Player foundBinary = binaryPlayers.binarySearch(targetPPG);
        long endTimeBS = System.nanoTime();
        double timeBS = (endTimeBS - startTimeBS) / 1_000_000.0;

        // Untuk mendapatkan perbandingan searching saja
        PlayerList binarySearchOnly = allPlayers.copy();
        binarySearchOnly.mergeSort();
        binarySearchOnly.resetCounters();
        binarySearchOnly.binarySearch(targetPPG);

        System.out.println("\nBinary Search (termasuk waktu sorting data acak):");
        System.out.printf("  - Waktu eksekusi total: %.4f msec\n", timeBS);
        System.out.printf("  - Recursive Call saat Sorting: %d\n", binaryPlayers.getRecursiveCallCount());
        System.out.printf("  - Perbandingan saat Sorting: %d\n", binaryPlayers.getComparisonCount());
        System.out.printf("  - Pertukaran saat Sorting: Tidak dihitung (merge tidak swap)\n");
        System.out.printf("  - Perbandingan saat Searching: %d\n", binarySearchOnly.getComparisonCount());

        System.out.println("\nCatatan: Binary search membutuhkan data terurut,");
        System.out.println("         sehingga waktu dan operasi sorting termasuk dalam analisis.");
        System.out.println("======================================================================");

        // Generate performance table
        generatePerformanceTable();
    }

    private double findRealPPG(PlayerList players) {
        if (players.head == null) return 23.3;
        Player middle = players.getMiddlePlayer();
        return middle != null ? middle.ppg : players.head.ppg;
    }

    private void generatePerformanceTable() {
        System.out.println("\n+-------------------------------------------------------------------------------+");
        System.out.println("| Jumlah Data   | Linear Search | Binary Search | Merge Sort    | Quick Sort    |");
        System.out.println("+-------------------------------------------------------------------------------+");

        // Ukur performa untuk berbagai ukuran data
        performAndPrintForSize(100);
        performAndPrintForSize(200);
        performAndPrintForSize(300);

        System.out.println("+-------------------------------------------------------------------------------+");
    }

    private void performAndPrintForSize(int dataSize) {
        PlayerList subset = getSubsetOfPlayers(dataSize);
        
        if (subset.head == null) {
            System.out.printf("| %-14d| %-14s| %-14s| %-14s| %-14s|%n", 
                             dataSize, "N/A", "N/A", "N/A", "N/A");
            return;
        }

        double targetPPG = findRealPPG(subset);

        // Linear Search
        subset.resetCounters();
        long startTime = System.nanoTime();
        subset.linearSearch(targetPPG);
        long endTime = System.nanoTime();
        double linearTime = (endTime - startTime) / 1_000_000.0;

        // Binary Search (termasuk sorting)
        PlayerList binaryPlayers = subset.copy();
        binaryPlayers.resetCounters();
        startTime = System.nanoTime();
        binaryPlayers.mergeSort();
        binaryPlayers.binarySearch(targetPPG);
        endTime = System.nanoTime();
        double binaryTime = (endTime - startTime) / 1_000_000.0;

        // Merge Sort
        PlayerList mergePlayers = subset.copy();
        mergePlayers.resetCounters();
        startTime = System.nanoTime();
        mergePlayers.mergeSort();
        endTime = System.nanoTime();
        double mergeTime = (endTime - startTime) / 1_000_000.0;

        // Quick Sort
        PlayerList quickPlayers = subset.copy();
        quickPlayers.resetCounters();
        startTime = System.nanoTime();
        quickPlayers.quickSort();
        endTime = System.nanoTime();
        double quickTime = (endTime - startTime) / 1_000_000.0;

        System.out.printf("| %-14d| %-14.4f| %-14.4f| %-14.4f| %-14.4f|%n", 
                         dataSize, linearTime, binaryTime, mergeTime, quickTime);
    }

    private PlayerList getSubsetOfPlayers(int n) {
        PlayerList allPlayers = getAllPlayers();
        PlayerList subset = new PlayerList();
        Player current = allPlayers.head;
        int count = 0;
        while (current != null && count < n) {
            subset.addPlayer(current.name, current.jerseyNumber,
                           current.totalPoints, current.totalAssists,
                           current.totalRebounds, current.gamesPlayed);
            current = current.next;
            count++;
        }
        subset.calculateAverages();
        return subset;
    }
}