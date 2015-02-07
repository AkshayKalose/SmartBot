public class Logic {

    /**
     * Trust starts at 1.0 because as a baby, our minds are impressionable,
     * similarly, this bot trusts everything you say at first... but once you say
     * something false, the trust value will decrease by the set constant decrement.
     * value.
     */
    double trust = 1.0;
    final double DECREMENT = .01;
    public Logic () {
        
    }
}