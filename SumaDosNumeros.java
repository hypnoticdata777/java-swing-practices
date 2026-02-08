public class SumaDosNumeros {
    public static void main(String[] args) {
        int[] nums = {0, 2, 3, 7};
        int objetivo = 5;
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == objetivo) {
                    System.out.println(nums[i] + " + " + nums[j] + " = " + objetivo);
                    return;
                }
            }
        }
        System.out.println("No encontrado");
    }
}
