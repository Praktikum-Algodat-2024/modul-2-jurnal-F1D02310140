class Stack {
    private Buku top;

    public Stack() { 
        top = null; 
    }

    public boolean isEmpty() { 
        return top == null; 
    }

    public void push(Buku newBook) {
        newBook.setNext(top);
        top = newBook;
    }

    public Buku pop() {
        if (isEmpty()) return null;
        Buku item = top;
        top = top.getNext();
        return item;
    }

    public boolean isCursed(Buku buku, Antrian orang) {
        return buku != null && orang != null && buku.status_buku && !orang.spec_card;
    }

    public void swapStack(Buku cursedNode) {
        if (cursedNode == null || cursedNode == top) return;

        Buku current = top;
        Buku previous = null;

        while (current != null && current != cursedNode) {
            previous = current;
            current = current.getNext();
        }

        if (previous != null) {
            previous.setNext(cursedNode.getNext());
        }
        cursedNode.setNext(top);
        top = cursedNode;
    }

    public void displayStack(Antrian orang) {
        Buku current = top;
        boolean hasCursed = false;

        while (current != null) {
            System.out.println("=================================================");
            System.out.println("Judul Buku: " + current.judul);
            System.out.println("Pengarang: " + current.pengarang);
            System.out.println("Genre: " + current.genre);
            System.out.println("Status Buku: " + (current.status_buku ? "Cursed" : "Buku biasa"));
            System.out.println("=================================================");

            if (isCursed(current, orang)) {
                hasCursed = true;
            }
            current = current.getNext();
        }

        if (hasCursed) {
            current = top;

            while (current != null) {
                if (isCursed(current, orang)) {
                    swapStack(current);
                    break;
                }
                current = current.getNext();
            }

            current = top; 
            while (current != null) {
                System.out.println("=================================================");
                System.out.println("Judul Buku: " + current.judul);
                System.out.println("Pengarang: " + current.pengarang);
                System.out.println("Genre: " + current.genre);
                System.out.println("Status Buku: " + (current.status_buku ? "Cursed" : "Buku biasa"));
                System.out.println("=================================================");
                current = current.getNext();
            }

            pop();
            System.out.println();
            current = top; 
            while (current != null) {
                System.out.println("=================================================");
                System.out.println("Judul Buku: " + current.judul);
                System.out.println("Pengarang: " + current.pengarang);
                System.out.println("Genre: " + current.genre);
                System.out.println("Status Buku: " + (current.status_buku ? "Cursed" : "Buku biasa"));
                System.out.println("=================================================");
                current = current.getNext();
            }
        }
    }

    public int getLength() {
        Buku counter = top;
        int size = 0;
        while (counter != null) {
            size++;
            counter = counter.getNext();
        }
        return size;
    }
}

class Buku {
    String judul, pengarang, genre;
    Buku next = null;
    boolean status_buku;

    public Buku(String judul, String pengarang, String genre, boolean status_buku) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.genre = genre;
        this.status_buku = status_buku;
    }

    public Buku getNext() { 
        return this.next; 
    }

    public void setNext(Buku newNode) { 
        this.next = newNode; 
    }
}

class Antrian {
    Antrian next = null;
    String name;
    int jumlah_buku;
    boolean spec_card;
    Stack stack;

    public Antrian(String name, int jumlah_buku, boolean spec_card) {
        this.name = name;
        this.jumlah_buku = jumlah_buku;
        this.spec_card = spec_card;
        this.stack = new Stack(); 
    }

    public void displayBuku() {  
        System.out.println("=================================================");
        System.out.println("=\t\tBuku " + this.name + "\t\t\t=");
        this.stack.displayStack(this);  
    }

    public Antrian getNext() { 
        return this.next; 
    }

    public void setNext(Antrian newNode) { 
        this.next = newNode; 
    }
}

class Queue {
    Antrian front, rear;

    public Queue() {
        front = null;
        rear = null;
    }

    public void printQueue() {
        System.out.println("=================================================");
        System.out.println("=\t\tDAFTAR ANTRIAN\t\t\t=");
        System.out.println("=================================================");
        Antrian current = front;
        int urutAntrian = 1;

        while (current != null) {
            System.out.println("Nama: " + current.name);
            System.out.println("Antrian ke: " + urutAntrian);
            System.out.println("Jumlah Buku: " + current.stack.getLength());
            System.out.println("Kartu Spesial: " + (current.spec_card ? "Ada" : "Tidak ada"));
            System.out.println("=================================================");
            current = current.getNext();
            urutAntrian++;
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Antrian newQueue) {
        if (rear != null) {
            rear.setNext(newQueue);
        }
        rear = newQueue;
        if (front == null) {
            front = rear;
        }
    }

    public void swapQueue(Antrian orang1, Antrian orang2) {
        if (orang1 == null || orang2 == null) {
            return;
        }
        Antrian current = front;
        Antrian previous = null;

        while (current != null && current != orang2) {
            previous = current;
            current = current.getNext();
        }
        if (previous != null) {
            previous.setNext(orang2.getNext());
        }
        orang2.setNext(front);
        front = orang2;
    }

    public Antrian dequeue() {
        if (isEmpty()) return null;
        Antrian removedQueue = front;
        front = front.getNext();
        if (front == null) { 
            rear = null; 
        }
        return removedQueue;
    }

    public void tempStack(Antrian antrian) {
        if (antrian == front) {
            front = front.getNext(); 
        } else {
            Antrian counter = front;
            while (counter != null) {
                if (counter.getNext() == antrian) {
                    counter.setNext(antrian.getNext());
                }
                counter = counter.getNext();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Queue listAntrian = new Queue();
        
        Antrian orang1 = new Antrian("Kazuma", 2, false);
        orang1.stack.push(new Buku("Belajar Java", "Raysen", "Edukasi", false));
        orang1.stack.push(new Buku("Cara Menjadi Orang Kaya", "Teguh", "Fantasi", false));
        listAntrian.enqueue(orang1);

        Antrian orang2 = new Antrian("Hu Tao", 3, true);
        orang2.stack.push(new Buku("Cara Tidur Cepat", "Teguh", "Edukasi Kayaknya", true));
        orang2.stack.push(new Buku("Belajar C++", "Raysen", "Edukasi", false));
        orang2.stack.push(new Buku("Belajar Ilmu Hitam", "Megumin", "Unknwon", true));
        listAntrian.enqueue(orang2);

        Antrian orang3 = new Antrian("Kafka", 3, false);
        orang3.stack.push(new Buku("Raysen the Forgotten One", "Unknown", "Sejarah", true));
        orang3.stack.push(new Buku("Misteri Menghilangnya Nasi Puyung", "Optimus", "Misteri", false));
        orang3.stack.push(new Buku("Cara Menjadi Milioner Dalam 1 Jam", "Unknown", "Sejarah", true));
        listAntrian.enqueue(orang3);

        Antrian orang4 = new Antrian("Xiangling", 1, false);
        orang4.stack.push(new Buku(null, null, null, false));
        listAntrian.enqueue(orang4);
        listAntrian.printQueue();
         
        System.out.println();
        orang1.displayBuku();
        listAntrian.dequeue();
        listAntrian.printQueue();

        orang2.displayBuku();
        listAntrian.dequeue();
        listAntrian.printQueue();

        Antrian orang5 = new Antrian("Sucrose", 3, true);
        orang5.stack.push(new Buku("Resurection", "Unknown", "Unknown", true));
        orang5.stack.push(new Buku("Alchemy", "Albedo", "Sience", true));
        orang5.stack.push(new Buku("Durin the Forgotten Dragon", "Gold", "Misteri", false));

        listAntrian.enqueue(orang5);

        listAntrian.tempStack(orang4);
        listAntrian.printQueue();
        System.out.println();
        listAntrian.swapQueue(orang3, orang5);
        listAntrian.printQueue();

        orang5.displayBuku();
        listAntrian.dequeue();
        listAntrian.printQueue();
        orang3.displayBuku();
    }
}
