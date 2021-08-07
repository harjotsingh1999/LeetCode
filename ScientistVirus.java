public class ScientistVirus {

    /**
     * .The question was: There is a scientist, and he has to perform experiments on
     * some virus. But he can perform the experiment if there is only 1 virus. GIven
     * number of virus between [1,10^18], find the minimum number of steps to reduce
     * the number of viruses using the below steps:-
     * 
     * Add or subtract 1 to the virus count. May reduce the size by half if the
     * count is even.
     */

    public static void minimumSteps(long virusCount) {
        System.out.println(countMinSteps(virusCount));
    }

    private static long countMinSteps(long virusCount) {
        if (virusCount == 1)
            return 0;

        if (virusCount % 2 == 0)
            return 1 + countMinSteps(virusCount - 1);
        else
            return 1 + Math.min(countMinSteps((virusCount + 1) / 2), countMinSteps((virusCount - 1) / 2));
    }

    public static void main(String[] args) {
        ScientistVirus.minimumSteps(3);
    }
}
