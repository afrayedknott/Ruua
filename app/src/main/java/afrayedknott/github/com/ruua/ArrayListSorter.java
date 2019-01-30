package afrayedknott.github.com.ruua;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSorter {

    ArrayList<User> arrayListToSort;
    ArrayList<String> arrayListSortable;

    public ArrayListSorter(ArrayList<User> inputUserList) {

        arrayListToSort = inputUserList;
        arrayListSortable = new ArrayList<String>(0);

    }

    public void setArrayListToSort(ArrayList<User> inputUserList){
        this.arrayListToSort = inputUserList;
    }

    public ArrayList<User> getArrayListToSort(){return arrayListToSort;}

    public void sortFirstNameAscending() {

        String tempToSort = null;
        String tempToApplyOrder = null;
        ArrayList<User> tempArrayListUser = new ArrayList<User>(0);

        //ordering
        for (int iterateToOrder = 0; iterateToOrder < arrayListToSort.size()-1; iterateToOrder++){

            tempToSort =
                    arrayListToSort.get(iterateToOrder).getFirstName() +
                            arrayListToSort.get(iterateToOrder).getLastName() +
                            arrayListToSort.get(iterateToOrder).getEmployeeID();
            arrayListSortable.add(tempToSort);

        }

        Collections.sort(arrayListSortable);

        //apply the ordering to arrayListToSort
        for (int iterateToApplyOrder = 0; iterateToApplyOrder < arrayListSortable.size()-1; iterateToApplyOrder++){

            tempToSort = arrayListSortable.get(iterateToApplyOrder);

            for (int iterateSort = 0; iterateSort < arrayListToSort.size()-1; iterateSort++){

                tempToApplyOrder =
                        arrayListToSort.get(iterateSort).getFirstName() +
                                arrayListToSort.get(iterateSort).getLastName() +
                                arrayListToSort.get(iterateSort).getEmployeeID();

                if(tempToSort.equals(tempToApplyOrder)) {
                    tempArrayListUser.add(arrayListToSort.get(iterateToApplyOrder));
                }

            }

        }

        //replace input ArrayList with new temp ArrayList that is ordered
        arrayListToSort = tempArrayListUser;

    }

    public void sortFirstNameDescending() {

        String tempToSort = null;
        String tempToApplyOrder = null;
        ArrayList<User> tempArrayListUser = new ArrayList<User>(0);

        //ordering
        for (int iterateToOrder = 0; iterateToOrder < arrayListToSort.size()-1; iterateToOrder++){

            tempToSort =
                    arrayListToSort.get(iterateToOrder).getFirstName() +
                            arrayListToSort.get(iterateToOrder).getLastName() +
                            arrayListToSort.get(iterateToOrder).getEmployeeID();
            arrayListSortable.add(tempToSort);

        }

        Collections.sort(arrayListSortable);
        Collections.reverse(arrayListSortable);

        //apply the ordering to arrayListToSort
        for (int iterateToApplyOrder = 0; iterateToApplyOrder < arrayListSortable.size()-1; iterateToApplyOrder++){

            tempToSort = arrayListSortable.get(iterateToApplyOrder);

            for (int iterateSort = 0; iterateSort < arrayListToSort.size()-1; iterateSort++){

                tempToApplyOrder =
                        arrayListToSort.get(iterateSort).getFirstName() +
                                arrayListToSort.get(iterateSort).getLastName() +
                                arrayListToSort.get(iterateSort).getEmployeeID();

                if(tempToSort.equals(tempToApplyOrder)) {
                    tempArrayListUser.add(arrayListToSort.get(iterateToApplyOrder));
                }

            }

        }

        //replace input ArrayList with new temp ArrayList that is ordered
        arrayListToSort = tempArrayListUser;

    }

    public void sortLastNameAscending() {

        String tempToSort = null;
        String tempToApplyOrder = null;
        ArrayList<User> tempArrayListUser = new ArrayList<User>(0);

        //ordering
        for (int iterateToOrder = 0; iterateToOrder < arrayListToSort.size()-1; iterateToOrder++){

            tempToSort =
                    arrayListToSort.get(iterateToOrder).getLastName() +
                            arrayListToSort.get(iterateToOrder).getFirstName() +
                            arrayListToSort.get(iterateToOrder).getEmployeeID();
            arrayListSortable.add(tempToSort);

        }

        Collections.sort(arrayListSortable);

        //apply the ordering to arrayListToSort
        for (int iterateToApplyOrder = 0; iterateToApplyOrder < arrayListSortable.size()-1; iterateToApplyOrder++){

            tempToSort = arrayListSortable.get(iterateToApplyOrder);

            for (int iterateSort = 0; iterateSort < arrayListToSort.size()-1; iterateSort++){

                tempToApplyOrder =
                        arrayListToSort.get(iterateSort).getLastName() +
                                arrayListToSort.get(iterateSort).getFirstName() +
                                arrayListToSort.get(iterateSort).getEmployeeID();

                if(tempToSort.equals(tempToApplyOrder)) {
                    tempArrayListUser.add(arrayListToSort.get(iterateToApplyOrder));
                }

            }

        }

        //replace input ArrayList with new temp ArrayList that is ordered
        arrayListToSort = tempArrayListUser;

    }

    public void sortLastNameDescending() {

        String tempToSort = null;
        String tempToApplyOrder = null;
        ArrayList<User> tempArrayListUser = new ArrayList<User>(0);

        //ordering
        for (int iterateToOrder = 0; iterateToOrder < arrayListToSort.size()-1; iterateToOrder++){

            tempToSort =
                    arrayListToSort.get(iterateToOrder).getLastName() +
                            arrayListToSort.get(iterateToOrder).getFirstName() +
                            arrayListToSort.get(iterateToOrder).getEmployeeID();
            arrayListSortable.add(tempToSort);

        }

        Collections.sort(arrayListSortable);
        Collections.reverse(arrayListSortable);

        //apply the ordering to arrayListToSort
        for (int iterateToApplyOrder = 0; iterateToApplyOrder < arrayListSortable.size()-1; iterateToApplyOrder++){

            tempToSort = arrayListSortable.get(iterateToApplyOrder);

            for (int iterateSort = 0; iterateSort < arrayListToSort.size()-1; iterateSort++){

                tempToApplyOrder =
                        arrayListToSort.get(iterateSort).getLastName() +
                                arrayListToSort.get(iterateSort).getFirstName() +
                                arrayListToSort.get(iterateSort).getEmployeeID();

                if(tempToSort.equals(tempToApplyOrder)) {
                    tempArrayListUser.add(arrayListToSort.get(iterateToApplyOrder));
                }

            }

        }

        //replace input ArrayList with new temp ArrayList that is ordered
        arrayListToSort = tempArrayListUser;
    }



}

//TODO: create a more generalized elegant solution maybe using Comparator