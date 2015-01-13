/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import Modele.Equipe;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vanessa
 */

// Celui qui dépasse d'une huitre, te dépassse d'une ruise" 
public abstract class LdC implements fight{
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
    private Requetes r;
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
    
    
    public LdC(ArrayList<Equipe> liste_D1, ArrayList<Equipe> liste_D2 ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException // Les deux array list sont de 16 equipes normalement 
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
        r = new Requetes();
            
        
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
        ArrayList<Equipe> tmp = new ArrayList<>();
        int random=0;
        int tableau[]= new int[a.size()]; // Je crée un tableau d'entiers 
        ArrayList l = new ArrayList(); 
        for (int i = 0; i < a.size(); i++)
        {
            l.add(i);    
        }
        Collections.shuffle(l); // Mélange la liste d'entiers comprise dans l.
        
//          for (int i=0;i<l.size();i++)
//        {
//           tableau[i]=(int)l.get(i);  // J'ajoute les nombres mélangés dans le tableau d'entier ( plus lisible pour la suite)
//        }
        
        
        for (int i=0;i<l.size();i++)
        {
            tmp.add(a.get((int)l.get(i))); // J'ajoute l'équipe qui correspond au nombre dans la case du tableau corréspondante
         
        }
//         for (int i=0;i<l.size();i++)
//        {
//            tmp.add(a.get(tableau[i]));
//           
//        }
         
         a=tmp;
         
           for (int i=0;i<l.size();i++)
        {
           System.out.println(a.get(i).getNom());
           
        }
        
         
    } 
    


@Override
    public void fight(Equipe e1, Equipe e2) throws SQLException {

      double gagnant = Math.random();
        int nbButMarque = (int) (Math.random() * 100) % 5;
        if (nbButMarque == 0) {
            nbButMarque = 1;
        }
        int nbButEcaisse = (int) (Math.random() * 100) % 5;
        if (nbButEcaisse >= nbButMarque) {
            nbButEcaisse = nbButMarque - 1;
        }

        if (e1 == e2) {
            System.out.println("Erreur même equipe");

        } else if (e1 != e2) {
            if (e1.getDeja_joue().contains(e2)) {
                System.out.println("Equipe déjà affrontée");
                gagnant = -1;
            }
            
            if (gagnant != -1) {
                if (gagnant > -1 && gagnant < 0.5) {
                    //Equipe gagnante
                    e1.setJ(e1.getJ() + 1);
                    e1.setG(e1.getG() + 1);
                    e1.setPts(e1.getPts() + 3);
                    e1.setBP(e1.getBP() + nbButMarque);
                    e1.setBC(e1.getBC() + nbButEcaisse);
                    e1.setDiff(e1.getBP() - e1.getBC());

                    //Equipe perdante
                    e2.setJ(e2.getJ() + 1);
                    e2.setP(e2.getP() + 1);
                    e2.setPts(e2.getPts() - 1);
                    e2.setBP(e2.getBP() + nbButEcaisse);
                    e2.setBC(e2.getBC() + nbButMarque);
                    e2.setDiff(e2.getBP() - e2.getBC());

                    System.err.println("Gagnante : " + e1.getNom());
                } else if (gagnant == 0.5) {
                    nbButEcaisse = nbButMarque;

                    //Equipe 2
                    e2.setJ(e2.getJ() + 1);
                    e2.setN(e2.getN() + 1);
                    e2.setPts(e2.getPts() + 1);
                    e2.setBP(e2.getBP() + nbButMarque);
                    e2.setBC(e2.getBC() + nbButEcaisse);
                    e2.setDiff(e2.getBP() - e2.getBC());

                    //Equipe 1
                    e1.setJ(e1.getJ() + 1);
                    e1.setP(e1.getP() + 1);
                    e1.setPts(e1.getPts() + 1);
                    e1.setBP(e1.getBP() + nbButEcaisse);
                    e1.setBC(e1.getBC() + nbButMarque);
                    e1.setDiff(e1.getBP() - e1.getBC());

                    System.err.println("Match Nul");
                } else {
                    //Equipe gagnante
                    e2.setJ(e2.getJ() + 1);
                    e2.setG(e2.getG() + 1);
                    e2.setPts(e2.getPts() + 3);
                    e2.setBP(e2.getBP() + nbButMarque);
                    e2.setBC(e2.getBC() + nbButEcaisse);
                    e2.setDiff(e2.getBP() - e2.getBC());

                    //Equipe perdante
                    e1.setJ(e1.getJ() + 1);
                    e1.setP(e1.getP() + 1);
                    e1.setPts(e1.getPts() - 1);
                    e1.setBP(e1.getBP() + nbButEcaisse);
                    e1.setBC(e1.getBC() + nbButMarque);
                    e1.setDiff(e1.getBP() - e1.getBC());

                    System.err.println("Gagnante : " + e2.getNom());
                }
            r.MAJBd(e1, "champions league");
            r.MAJBd(e2, "champions league");
            e1.getDeja_joue().add(e2);
            e2.getDeja_joue().add(e1);

              

          ArrayList<Equipe> tmp =  r.Classement(e1.getPays(), "champions league");
         //   division2.clear();
             //     division2.addAll(tmp);
                for (int j = 0; j < tmp.size(); j++) {
                    for (int i = 0; i < this.liste_LDC.size(); i++) {
                        if (this.liste_LDC.get(i).getNom().equals(tmp.get(j).getNom())) {
                            this.liste_LDC.get(i).setClassement(tmp.get(j).getClassement());
                        }

//                    if (division2.contains(e1)) {
//                        division2.get(i).setDeja_joue(e1.getDeja_joue());
//                    } else if (division2.get(i).getNom().equals(e2.getNom())) {
//                        division2.get(i).setDeja_joue(e2.getDeja_joue());
//                    }
                    }
                }
                
            }
        }
         // r.envoiEuropa(e2);
       // echangeD1D2();
    }
      
    }

        
       
       
       
            
    
     
    
    

