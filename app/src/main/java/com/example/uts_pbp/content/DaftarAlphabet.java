package com.example.uts_pbp.content;

import java.util.ArrayList;

public class DaftarAlphabet {
    public ArrayList<DataAlphabet> DataAlphabet;
    public DaftarAlphabet() {
        DataAlphabet = new ArrayList();
        DataAlphabet.add(A);
        DataAlphabet.add(B);
        DataAlphabet.add(C);
        DataAlphabet.add(D);
        DataAlphabet.add(E);
        DataAlphabet.add(F);
        DataAlphabet.add(G);
        DataAlphabet.add(H);
        DataAlphabet.add(I);
    }

    public static final DataAlphabet A = new DataAlphabet("A","a", "Apel", "https://i.picsum.photos/id/557/200/200.jpg?hmac=Y3oVAxcM0NGQ6OBLGhCOaRI9_TBDXdJFB8547MBovxU");
    public static final DataAlphabet B = new DataAlphabet("B","b", "Buku", "https://i.picsum.photos/id/557/200/200.jpg?hmac=Y3oVAxcM0NGQ6OBLGhCOaRI9_TBDXdJFB8547MBovxU");
    public static final DataAlphabet C = new DataAlphabet("C","c", "Celana", "https://i.picsum.photos/id/557/200/200.jpg?hmac=Y3oVAxcM0NGQ6OBLGhCOaRI9_TBDXdJFB8547MBovxU");
    public static final DataAlphabet D = new DataAlphabet("D","d", "Dompet", "https://i.picsum.photos/id/557/200/200.jpg?hmac=Y3oVAxcM0NGQ6OBLGhCOaRI9_TBDXdJFB8547MBovxU");
    public static final DataAlphabet E = new DataAlphabet("E","e", "Elang", "https://i.picsum.photos/id/557/200/200.jpg?hmac=Y3oVAxcM0NGQ6OBLGhCOaRI9_TBDXdJFB8547MBovxU");
    public static final DataAlphabet F = new DataAlphabet("F","f", "Foto", "https://i.picsum.photos/id/557/200/200.jpg?hmac=Y3oVAxcM0NGQ6OBLGhCOaRI9_TBDXdJFB8547MBovxU");
    public static final DataAlphabet G = new DataAlphabet("G","g", "Gelang", "https://i.picsum.photos/id/557/200/200.jpg?hmac=Y3oVAxcM0NGQ6OBLGhCOaRI9_TBDXdJFB8547MBovxU");
    public static final DataAlphabet H = new DataAlphabet("H","h", "Hidung", "https://i.picsum.photos/id/557/200/200.jpg?hmac=Y3oVAxcM0NGQ6OBLGhCOaRI9_TBDXdJFB8547MBovxU");
    public static final DataAlphabet I = new DataAlphabet("I","i", "Ikan", "https://i.picsum.photos/id/557/200/200.jpg?hmac=Y3oVAxcM0NGQ6OBLGhCOaRI9_TBDXdJFB8547MBovxU");
}
