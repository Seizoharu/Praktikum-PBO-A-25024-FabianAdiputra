public class Main{
    public static void main(String[] args){
        System.out.println("=== List Mahasiswa ===");
        Mahasiswa[] mhs={
            new Mahasiswa("Khairul Adam Efendi","4525210035",75),
            new Mahasiswa("Ilham Baihaqi","4525210029",90),
            new Mahasiswa("Kahlil","4525210030",70),
            new Mahasiswa("Karel Abimanyu Ahpandi","4525210031",65),
            new Mahasiswa("Kevin Samuel","4525210033",20),
            new Mahasiswa("Chelsy","4525210028",70),
            new Mahasiswa("Harisa","4525210027",80),
            new Mahasiswa("Ghatfan","4525210025",65),
            new Mahasiswa("Fabian","4525210024",100),
            new Mahasiswa("Rakha","4524210082",50)
        };
        for(Mahasiswa m : mhs){
            System.out.println(" "+ m.tampilkanInfo());
        }
        System.out.println();
        System.out.println("=== Jadwal Mata Kuliah ===");
        Jadwal[] jdwl={
            new Jadwal("Senin","08:00","10:30","Basis Data","309"),
            new Jadwal("Senin","10:30","12:00","Prak Basis Data","Lab SE"),
            new Jadwal("Selasa","08:00","10:30","Desain Analisis Algoritma","Online"),
            new Jadwal("Selasa","11:00","13:30","Prak Desain Web","Lab SE")
        };

        for(Jadwal j:jdwl){
            System.out.println(" "+j.toString());
        }
    }   
}