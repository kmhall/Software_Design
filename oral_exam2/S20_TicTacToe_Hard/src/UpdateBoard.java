import javax.swing.*;

public class UpdateBoard extends SwingWorker {

    private final JLabel[] labelPosition;
    private int index;
    private String xOry;

    public UpdateBoard(JLabel[] labelPosition, int index, String xORy){
        this.labelPosition = labelPosition;
        this.index = index;
        this.xOry = xORy;

    }

    @Override
    protected Object doInBackground() throws Exception {


        return null;
    }

    protected void done(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        labelPosition[index].setText(xOry);

    }
}
