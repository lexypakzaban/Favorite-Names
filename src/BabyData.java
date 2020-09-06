import java.util.ArrayList;

public class BabyData
{
    private String name;
    private ArrayList<Integer> ranks;

    /**
     * Receives a string that corresponds to one line of the data file
     * e.g.: "Harlan	354	342	307	284	369	506	608	915	0	0	0	0"
     * Splits this up by tabs, and fills in the "name" and the "ranks" variables.
     * @param line - a line of information about one babyname.
     */
    public BabyData(String line)
    {
        //TODO: #0 You write this method!
        //DONE!
        ranks = new ArrayList<Integer>();
        String[] parts = line.split("\t");
        name = parts[0];

        for (int i = 1; i < parts.length; i++){
            ranks.add(Integer.parseInt(parts[i]));
        }

    }



    public String getName()
    {
        return name;
    }

    public ArrayList<Integer> getRanks()
    {
        return ranks;
    }

    @Override
    public String toString()
    {
        String result = name;
        for (int rank : ranks)
        {
            result += "\t" + rank;
        }
        return result;
    }
}
