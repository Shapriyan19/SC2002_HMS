package hmsApp;

import appointment.*;
import user.*;

import java.util.Scanner;
import java.util.List;
public class PatientUI {
    
    private Patient patient;
    private Scanner scanner;

    public PatientUI(Patient patient){
        this.patient = patient;
        this.scanner=new Scanner(System.in);
    }

    public void showMenu(){
        
    }
}
