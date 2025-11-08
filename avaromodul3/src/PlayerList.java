public class PlayerList {
    Player head;
    Player tail;
    private int comparisonCount;
    private int swapCount;
    private int recursiveCallCount;

    public PlayerList() {
        this.head = null;
        this.tail = null;
        this.comparisonCount = 0;
        this.swapCount = 0;
        this.recursiveCallCount = 0;
    }

    // Menghitung dan melakukan operasi dasarnya dulu yakan di sini...

    public void addPlayer(String name, int jerseyNumber, double totalPoints,
                         double totalAssists, double totalRebounds, int gamesPlayed) {
        Player newPlayer = new Player(name, jerseyNumber, totalPoints, totalAssists, totalRebounds, gamesPlayed);
        
        if (this.head == null) {
            this.head = newPlayer;
            this.tail = newPlayer;
        } else {
            newPlayer.prev = this.tail;
            this.tail.next = newPlayer;
            this.tail = newPlayer;
        }
    }

    public int getSize() {
        int count = 0;
        Player current = this.head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Method yang berguna untuk menghitung rata-ratanya

    public void calculateAverages() {
        Player current = this.head;
        while (current != null) {
            current.ppg = roundToOneDecimal(current.totalPoints / current.gamesPlayed);
            current.apg = roundToOneDecimal(current.totalAssists / current.gamesPlayed);
            current.rpg = roundToOneDecimal(current.totalRebounds / current.gamesPlayed);
            current = current.next;
        }
    }

    private double roundToOneDecimal(double value) {
        return ((int)(value * 10 + 0.5)) / 10.0;
    }

    // Method untuk menampilkan 
    public void display() {
        if (this.head == null) {
            System.out.println("Daftar pemain kosong.");
            return;
        }

        System.out.println("======================================================================");
        System.out.printf("%-25s | %-8s | %-5s | %-5s | %-5s%n", 
                         "PLAYER NAME", "JERSEY #", "PPG", "APG", "RPG");
        System.out.println("----------------------------------------------------------------------");
        
        Player current = this.head;
        while (current != null) {
            System.out.printf("%-25s | %-8d | %-5.1f | %-5.1f | %-5.1f%n", 
                            current.name, current.jerseyNumber, current.ppg, current.apg, current.rpg);
            current = current.next;
        }
        System.out.println("======================================================================");
    }

    public void displayWithHeader(String header) {
        System.out.println("\n" + header);
        display();
    }

    // MERGE SORT
    public void mergeSort() {
        resetCounters();
        if (this.head != null) {
            this.head = mergeSort(this.head);
            updateTail(); // Update tail setelah sorting
        }
    }

    private Player mergeSort(Player head) {
        recursiveCallCount++;
        
        if (head == null || head.next == null) {
            return head;
        }
	
	// Eits... bagi dua
        Player middle = getMiddle(head);
        Player nextOfMiddle = middle.next;
        middle.next = null;
        if (nextOfMiddle != null) {
            nextOfMiddle.prev = null;
        }
	
	// Urutkan kedua bagian secara rekursif
        Player left = mergeSort(head);
        Player right = mergeSort(nextOfMiddle);

	// Dimerge dari sort 
        return merge(left, right);
    }

    private Player getMiddle(Player head) {
        if (head == null) return head;

        Player slow = head;
        Player fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Player merge(Player left, Player right) {
        if (left == null) return right;
        if (right == null) return left;
	
	// Bandingin PPG secara descending
        comparisonCount++;
        Player result;

        if (left.ppg >= right.ppg) {
            result = left;
            result.next = merge(left.next, right);
            if (result.next != null) {
                result.next.prev = result;
            }
        } else {
            result = right;
            result.next = merge(left, right.next);
            if (result.next != null) {
                result.next.prev = result;
            }
        }
        result.prev = null;
        return result;
    }

    private void updateTail() {
        if (this.head == null) {
            this.tail = null;
            return;
        }

        Player current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        this.tail = current;
    }

    // QUICK SORT
    public void quickSort() {
        resetCounters();
        if (this.head != null) {
            quickSort(this.head, this.tail);
        }
    }

    private void quickSort(Player low, Player high) {
        if (low != null && high != null && low != high && low != high.next) {
            recursiveCallCount++;
            Player pivot = partition(low, high);
            quickSort(low, pivot.prev);
            quickSort(pivot.next, high);
        }
    }

    private Player partition(Player low, Player high) {
	// Menjadikan si tail alias node terakhir pivot
        Player pivot = high;
        Player i = low.prev;

        for (Player j = low; j != high; j = j.next) {
            comparisonCount++;
		// Sort descending berdasarkan PPG
            if (j.ppg >= pivot.ppg) {
                i = (i == null) ? low : i.next;
                swapData(i, j);
                swapCount++;
            }
        }

        i = (i == null) ? low : i.next;
        swapData(i, high);
        swapCount++;
        return i;
    }

	// Swap semua data dari 2 player
    private void swapData(Player a, Player b) {
        String tempName = a.name;
        int tempJersey = a.jerseyNumber;
        double tempPoints = a.totalPoints;
        double tempAssists = a.totalAssists;
        double tempRebounds = a.totalRebounds;
        int tempGames = a.gamesPlayed;
        double tempPPG = a.ppg;
        double tempAPG = a.apg;
        double tempRPG = a.rpg;

        a.name = b.name;
        a.jerseyNumber = b.jerseyNumber;
        a.totalPoints = b.totalPoints;
        a.totalAssists = b.totalAssists;
        a.totalRebounds = b.totalRebounds;
        a.gamesPlayed = b.gamesPlayed;
        a.ppg = b.ppg;
        a.apg = b.apg;
        a.rpg = b.rpg;

        b.name = tempName;
        b.jerseyNumber = tempJersey;
        b.totalPoints = tempPoints;
        b.totalAssists = tempAssists;
        b.totalRebounds = tempRebounds;
        b.gamesPlayed = tempGames;
        b.ppg = tempPPG;
        b.apg = tempAPG;
        b.rpg = tempRPG;
    }

    // SEARCHING / PENCARI 

    // SEQUENTIAL SORT / LINEAR SORT
    public Player linearSearch(double targetPPG) {
        Player current = this.head;
        while (current != null) {
            comparisonCount++;
            if (current.ppg == targetPPG) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
	
	 // BINARY SEARCH
    public Player binarySearch(double targetPPG) {
        if (this.head == null) {
            return null;
        }
        
        Player left = this.head;
        Player right = this.tail;
        
        while (left != null && right != null && left != right.next) {
            Player mid = getMiddleBetween(left, right);
            if (mid == null) {
                break;
            }
            
            comparisonCount++;
            if (mid.ppg == targetPPG) {
                return mid;
            } else if (mid.ppg > targetPPG) {
                left = mid.next;
            } else {
                right = mid.prev;
            }
        }
        return null;
    }

    private Player getMiddleBetween(Player start, Player end) {
        if (start == null) return null;
        
        Player slow = start;
        Player fast = start;
        
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Manajemen counternya di sini

    public void resetCounters() {
        this.comparisonCount = 0;
        this.swapCount = 0;
        this.recursiveCallCount = 0;
    }

    public int getComparisonCount() {
        return comparisonCount;
    }

    public int getSwapCount() {
        return swapCount;
    }

    public int getRecursiveCallCount() {
        return recursiveCallCount;
    }

    // Method sebagai utilitasnya
    public PlayerList copy() {
        PlayerList copyList = new PlayerList();
        Player current = this.head;
        while (current != null) {
            copyList.addPlayer(current.name, current.jerseyNumber, 
                             current.totalPoints, current.totalAssists, 
                             current.totalRebounds, current.gamesPlayed);
            current = current.next;
        }
        copyList.calculateAverages();
        return copyList;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public Player getMiddlePlayer() {
        if (this.head == null) return null;
        
        Player slow = this.head;
        Player fast = this.head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
}
