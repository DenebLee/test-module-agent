package nanoit.kr.cli;

public class Configure {
    public static void main(String[] args) {
        try {
            Branch branch = new Branch();
            branch.mainBranch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
