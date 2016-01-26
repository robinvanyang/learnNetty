package name.redbud;

/**
 * TODO
 * Created By: Robin Yang
 * Created At: 2016-01-15 14:45
 */
public class RuinLambdas {
    public static void main(String[] args) {
        // Easy
        startDream(new Dream() {
            @Override public void dream() {
                // Dream boy, dream ...
            }
        });

        startDream(new AugmentedDream() {
            @Override public void dream(String dreamName) {
                // Dream boy, dream ...
            }
        });

        // Less easy
        startDream(() -> { /* Dream boy, dream ... */ }); //line 1

        // And now kid ? ...
        startDream((dreamName) -> { /* Dream boy, dream ... */ });

        // Do you see which one it is directly by reading this ? ...
        startDream((dreamName, duration) -> { /* Dream boy, dream ... */ });
    }

    public static void startDream(Dream md) { md.dream(); }

    //开始做美梦
    public static boolean startDream(AugmentedDream ad) {
        ad.dream("Ruin lambdas");
        return true;
    }

    //开始做噩梦
    public static boolean startDream(ThisIsNightmare hahaha) {
        hahaha.sufferMyKid("Hahaha you're going to hell", 2000);
        return false;
    }
}

interface Dream {
    void dream();
}

interface AugmentedDream {
    void dream(String dreamName);
}

interface ThisIsNightmare {
    void sufferMyKid(String dreamName, int duration);
}
