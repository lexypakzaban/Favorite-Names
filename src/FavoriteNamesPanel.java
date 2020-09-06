import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class FavoriteNamesPanel extends JPanel
{
    private ArrayList<BabyData> myData;
    private ArrayList<String> namesIShouldPlot;

    //constants - feel free to adjust.
    private final int MARGIN_TOP = 16;
    private final int MARGIN_BOTTOM = 32;
    private final int MARGIN_LEFT = 32;
    private final int MARGIN_RIGHT = 32;
    private final int MAX_RANK = 1005; // gives a little gap before the not-in-top-1000 level.



    public FavoriteNamesPanel()
    {
        super();
        setBackground(Color.WHITE);
        loadData();
    }

    public void loadData()
    {
        // TODO: #1 Initialize 'myData' and 'namesIShouldPlot' class variables.
        //DONE
        myData = new ArrayList<BabyData>();
        namesIShouldPlot = new ArrayList<String>();

        File dataFile = new File("names-data-2010.txt");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line;
            do
            {
                line = reader.readLine();
                if (line != null)
                {
                    //TODO: #2 Create a new BabyData, passing the line along as a
                    // parameter to the constructor.
                    //DONE
                    BabyData newBaby = new BabyData(line);

                    // TODO: #3 Add the BabyData to your list of babies.
                    //DONE
                    myData.add(newBaby);

                }
            }while (line != null);
            reader.close();

        }catch(FileNotFoundException fnfExcp)
        {
            System.out.println("Could not open file.");
            fnfExcp.printStackTrace();
        }
        catch (IOException ioExcp)
        {
            System.out.println("IO Exception");
            ioExcp.printStackTrace();
        }


    }

    /**
     * The user has typed in a string and pressed the "Add" button; this
     * method will add this name to the list of names to draw.
     * @param name - the name to add.
     */
    public void addName(String name)
    {
        System.out.println("Panel is adding name: "+name);

        // TODO: #4 You write this method.
        //DONE

        // At the base level, this might be very short....
        namesIShouldPlot.add(name);


        // Optional: check whether this baby name is in your
        // data before adding it to the list of names to plot.

        // Optional: also check whether this name is already
        // in your names to plot before adding it again.


        repaint(); // this is what makes the paintComponent() happen.

        // temporary code follows to test whether this worked.
        System.out.println("Names to plot after add:");
        debug_ListAllNamesToPlot();
        System.out.println("-----------------------");
    }

    /**
     * The user has clicked the "Remove" button. Remove at least one
     * instance of this name from the list, if there is one.
     * @param nameToRemove - the name to remove
     */
    public void removeName(String nameToRemove)
    {
        // TODO: #5 You write this method.
        //DONE
        namesIShouldPlot.remove(nameToRemove);


        repaint(); // this is what makes the paintComponent() happen.

        // temporary code follows to test whether this worked.
        System.out.println("Names to plot after remove:");
        debug_ListAllNamesToPlot();
        System.out.println("-----------------------");
    }

    /**
     * The user has clicked the "Clear" button. Remove all the names
     * from the list of names to draw.
     */
    public void clearNames()
    {
        // TODO: #6 You write this method.
        //DONE
        namesIShouldPlot.clear();

        repaint(); // this is what makes the paintComponent() happen.

        // temporary code follows to test whether this worked.
        System.out.println("Names to plot after clear:");
        debug_ListAllNamesToPlot();
        System.out.println("-----------------------");
    }

    /**
     * Check the list of baby data and return the ArrayList of ranks
     * for the given name, or null, if the name is not found.
     * @param name - name to search for
     * @return an ArrayList of rank integers, if the name is found
     * or null if it isn't.
     */
    public ArrayList<Integer> getRanksForName(String name)
    {
        // TODO: Optional. This looks like it would be a handy method.
        for (int i = 0; i < myData.size(); i ++){
            if (myData.get(i).getName().equals(name)) {
                return myData.get(i).getRanks();
            }

        }
        return null;
    }


    /**
     * finds the horizontal pixel location for a given year.
     * Optional - but you might find it handy.
     * @param year
     * @return the horizontal position in the window that corresponds to the year.
     */
    public int getXForYear(int year)
    {
        int index = (year-1900)/10;
        if (index<0 || index>11)
            return -1;
        return MARGIN_LEFT + index*(getWidth()-MARGIN_LEFT-MARGIN_RIGHT)/11;
    }

    /**
     * finds the vertical pixel location for a given rank: 1 is near
     * the top, 1000 is near the bottom, and 0 is just below 1000.
     * Optional - but you might find it handy.
     * @param rank
     * @return - the y-location of the given rank.
     */
    public int getYForRank(int rank)
    {
        if (rank == 0)
            return getHeight()-MARGIN_BOTTOM;
        return MARGIN_TOP + rank * (getHeight()-MARGIN_TOP-MARGIN_BOTTOM)/MAX_RANK;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //TODO: #7 Write this method to make the graph!
        //Draw axes
        Font myFont = new Font("Ariel", Font.PLAIN, 10);
        g.setFont(myFont);

        //y axis
        g.drawLine(MARGIN_LEFT, MARGIN_TOP, MARGIN_LEFT, (getHeight()-MARGIN_BOTTOM));

        for (int rank = 1000; rank > 0; rank = rank - 100){
            g.drawString("" + rank, 0, getYForRank(rank));
            g.drawString("—", MARGIN_LEFT - 5, getYForRank(rank));
        }

        //x axis
        g.drawLine(MARGIN_BOTTOM, (getHeight()-MARGIN_BOTTOM), MAX_RANK - MARGIN_RIGHT,
                (getHeight()-MARGIN_BOTTOM));

        for (int year = 1900; year <= 2010; year = year + 10){
            g.drawString(year + "", getXForYear(year), getHeight()-MARGIN_BOTTOM + MARGIN_TOP);
            g.drawString("|",getXForYear(year), getHeight()-MARGIN_BOTTOM+5);
        }


        //Loop through all the names in the list you have been given
            // draw the line for each name.

        for (String name: namesIShouldPlot){

            int year = 1900;
            ArrayList<Integer> ranks = getRanksForName(name);

            Color clr = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
            g.setColor(clr);


            try {

                for (int i = 0; i < ranks.size() - 1; i++) {
                    int xValueStarts = getXForYear(year);
                    int yValueStarts = getYForRank(ranks.get(i));

                    int xValueEnds = getXForYear(year + 10);
                    int yValueEnds = getYForRank(ranks.get(i + 1));

                    g.drawLine(xValueStarts, yValueStarts, xValueEnds, yValueEnds);

                    year = year + 10;

                    g.drawString(name, xValueEnds, yValueEnds);


                }
            }

            catch (NullPointerException e){
                System.out.println(name + " has never been listed in the top 1000 baby names");
            }

        }


    }

    public void debug_ListAllNamesToPlot()
    {
        for(String name: namesIShouldPlot)
        {
            for (BabyData datum: myData)
                if (datum.getName().equals(name))
                {
                    System.out.println(datum);
                    break;
                }
        }
    }
}
