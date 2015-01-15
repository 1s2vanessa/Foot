/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Bilal
 */

// Celui qui dépasse d'une huitre, te dépassse d'une ruise" 
public class LdC {
    private ArrayList<Equipe> liste_LDC ;
    private ArrayList<Equipe> groupe1 ;
    private ArrayList<Equipe> groupe2 ;
    private ArrayList<Equipe> groupe3 ;
    private ArrayList<Equipe> groupe4 ;
    private ArrayList<Equipe> groupe5 ;
    private ArrayList<Equipe> groupe6 ;
    private ArrayList<Equipe> groupe7 ;
    private ArrayList<Equipe> groupe8 ;
    private ArrayList<Equipe> groupe9 ;
    private ArrayList<Equipe> groupe10 ;
    private ArrayList<Equipe> groupe11;
    private ArrayList<Equipe> groupe12;
    
//
//    public LdC(ArrayList<Equipe> liste_D1, ArrayList<Equipe> liste_D2 ) 
//    {
//        liste_LDC=new ArrayList<>();
//       
//        for (int i=0;i<liste_D1.size();i++) // à tester
//        {
//            if (liste_D1.get(i)!=null)
//            {
//                liste_LDC.add(liste_D1.get(i));
//            }
//            
//        }
//        
//        for (int i=0;i<liste_D2.size();i++)
//        {
//            if (liste_D2.get(i)!=null)
//            {
//                 liste_LDC.add(liste_D2.get(i)); 
//            }
//        }  
//    }
//   
    
    
    public LdC(ArrayList<Equipe> liste_D1, ArrayList<Equipe> liste_D2 ) // Les deux array list sont de 16 equipes normalement 
    {   
        liste_LDC=new ArrayList<>();
        groupe1= new ArrayList<>();
        groupe2= new ArrayList<>();
        groupe3= new ArrayList<>();
        groupe4= new ArrayList<>();
        groupe5= new ArrayList<>();
        groupe6= new ArrayList<>();
        groupe7= new ArrayList<>();
        groupe8= new ArrayList<>();
        
        for (int i=0;i<liste_D1.size();i++) // à tester
        {
            if (liste_D1.get(i)!=null)
            {
                liste_LDC.add(liste_D1.get(i));
            }
            
            
        }
        
        for (int i=0;i<liste_D2.size();i++)
        {
            if (liste_D2.get(i)!=null)
            {
                 liste_LDC.add(liste_D2.get(i)); 
            }
           
        }
    }
    
    
    
    
   public void faire_8_groupes_de_4()
    {
        
        for (int i=0;i<4;i++)
        {
            groupe1.add(liste_LDC.get(i));
        }
        for (int i=4;i<8;i++)
        {
            groupe2.add(liste_LDC.get(i));
        }
        for (int i=8;i<12;i++)
        {
            groupe3.add(liste_LDC.get(i));
        }
         for (int i=12;i<16;i++)
        {
            groupe4.add(liste_LDC.get(i));
        }
          for (int i=16;i<20;i++)
        {
            groupe5.add(liste_LDC.get(i));
        }
           for (int i=20;i<24;i++)
        {
            groupe6.add(liste_LDC.get(i));
        }
            for (int i=24;i<28;i++)
        {
            groupe7.add(liste_LDC.get(i));
        }
                for (int i=28;i<32;i++)
        {
            groupe8.add(liste_LDC.get(i));
        }
    }
    
   public void faire_12_groupes_de_4()
    {
        
        for (int i=0;i<4;i++)
        {
            groupe1.add(liste_LDC.get(i));
        }
        for (int i=4;i<8;i++)
        {
            groupe2.add(liste_LDC.get(i));
        }
        for (int i=8;i<12;i++)
        {
            groupe3.add(liste_LDC.get(i));
        }
         for (int i=12;i<16;i++)
        {
            groupe4.add(liste_LDC.get(i));
        }
          for (int i=16;i<20;i++)
        {
            groupe5.add(liste_LDC.get(i));
        }
           for (int i=20;i<24;i++)
        {
            groupe6.add(liste_LDC.get(i));
        }
            for (int i=24;i<28;i++)
        {
            groupe7.add(liste_LDC.get(i));
        }
            for (int i=28;i<32;i++)
        {
            groupe8.add(liste_LDC.get(i));
        }
            for (int i=28;i<32;i++)
        {
            groupe9.add(liste_LDC.get(i));
        }
            for (int i=32;i<36;i++)
        {
            groupe10.add(liste_LDC.get(i));
        }
            for (int i=36;i<44;i++)
        {
            groupe11.add(liste_LDC.get(i));
        }
            for (int i=44;i<48;i++)
        {
            groupe12.add(liste_LDC.get(i));
        }
    }
    
    
   public void affiche_groupe(int numero)
    { 
        System.out.println("Groupe "+numero+";");
        
        switch (numero)
        {
            case 1:
            {
                 for (int i=0;i<4;i++)
                {

                    System.out.println(groupe1.get(i).getNom()); 

                }
                System.out.println("");
            }
            break;
                
                case 2:
            {
                 for (int i=0;i<4;i++)
                {

                    System.out.println(groupe2.get(i).getNom()); 

                }
                System.out.println("");
            } break;
            
            case 3:
            {
                 for (int i=0;i<4;i++)
                {

                    System.out.println(groupe3.get(i).getNom()); 

                }
                System.out.println("");
            }break;
                
                case 4:
            {
                 for (int i=0;i<4;i++)
                {

                    System.out.println(groupe4.get(i).getNom()); 

                }
                System.out.println("");
            }
            break;
                    
            case 5:
            {
                 for (int i=0;i<4;i++)
                {

                    System.out.println(groupe5.get(i).getNom()); 

                }
                System.out.println("");
            }
            break;
                
             case 6:
            {
                 for (int i=0;i<4;i++)
                {

                    System.out.println(groupe6.get(i).getNom()); 

                }
                System.out.println("");
            }
            break;   
                 
           case 7:
            {
                 for (int i=0;i<4;i++)
                {

                    System.out.println(groupe7.get(i).getNom()); 

                }
                System.out.println("");
            }
            break;
               
               
              
            case 8:
            {
                 for (int i=0;i<4;i++)
                {

                    System.out.println(groupe8.get(i).getNom()); 

                }
                System.out.println("");
            }
            break;
                
                
                
        }
    }

    public ArrayList<Equipe> getListe_LDC() {
        return liste_LDC;
    }
    

    
   public void melange_array(ArrayList<Equipe> a) 
    {
        //int nombre_equipes=a.size();
        ArrayList<Equipe> tmp = new ArrayList<>();
        
        int random=0;
        int tableau[]= new int[a.size()];
        ArrayList l = new ArrayList();
        for (int i = 1; i < 31; i++)
        l.add(i);
        Collections.shuffle(l); 
        
          for (int i=0;i<l.size();i++)
        {
           tableau[i]=(int)l.get(i);
        }
        
        
        for (int i=0;i<l.size();i++)
        {
            tmp.add(a.get((int)l.get(i)));
         
        }
         for (int i=0;i<l.size();i++)
        {
           System.out.println(tmp.get(i).getNom());
        }
    } 
    
}