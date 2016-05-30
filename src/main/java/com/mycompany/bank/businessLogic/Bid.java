package com.mycompany.bank.businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Bid extends AllBid {

    private Agreement agreement;
    int sum;

    public Bid(int id, Date date, int sum, Client client) {
        super(id, date, client);
        this.sum = sum;
        this.agreement = null;
    }

    public Bid(int id, Date date, Boolean responseClient, int sum, ResponseFinancier responseFinancier, Agreement agreement, Client client) {
        super(id, date, responseClient, responseFinancier, client);
        this.sum = sum;
        this.agreement = agreement;
    }

    public int getSum() {
        return this.sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    public static Bid getBid(List<Bid> bids, int id) {
        for (Bid bid : bids) {
            if (bid.getId() == id) {
                return bid;
            }
        }
        return null;
    }

    public static String[][] showBids(List<Bid> bids, Boolean manager) {
        if (bids == null) {
            return new String[][]{{null, null, null}};
        }
        List<Integer> index;
        if (manager) {
            index = getArrayForBidsManager(bids);
        } else {
            index = getArrayForBidsClient(bids);
        }
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][3];
        for (int i = 0; i < index.size(); i++) {
            Bid bid = bids.get(index.get(i));
            array[i][0] = Integer.toString(bid.getSum());
            array[i][1] = df.format(bid.getDate());
            if (manager) {
                array[i][2] = bid.getClient().getName();
            }
        }
        return array;
    }

    public static String[][] showApprovedBidsForClient(List<Bid> bids) {
        if (bids == null) {
            return new String[][]{{null, null, null, null, null}};
        }
        List<Integer> index = getArrayForApprovedBidsForClient(bids);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][5];

        for (int i = 0; i < index.size(); i++) {
            Bid bid = bids.get(index.get(i));
            array[i][0] = Integer.toString(bid.getSum());
            array[i][1] = df.format(bid.getDate());
            if (!bid.getResponseFinancier().getAnswer()) {
                array[i][2] = "-";
            } else {
                array[i][2] = "+";
                array[i][3] = Integer.toString(bid.getResponseFinancier().getTime());
                array[i][4] = Integer.toString(bid.getResponseFinancier().getPersent());
            }
        }
        return array;
    }

    public static String[][] showApprovedBidsForManager(List<Bid> bids) {
        if (bids == null) {
            return new String[][]{{null, null, null, null, null, null, null}};
        }
        List<Integer> index = getArrayForApprovedBidsForManager(bids);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][7];
        for (int i = 0; i < index.size(); i++) {
            Bid bid = bids.get(index.get(i));
            array[i][0] = Integer.toString(bid.getSum());
            array[i][1] = df.format(bid.getDate());
            array[i][2] = "+";
            array[i][3] = Integer.toString(bid.getResponseFinancier().getTime());
            array[i][4] = Integer.toString(bid.getResponseFinancier().getPersent());
            array[i][5] = bid.getClient().getName();
            array[i][6] = "+";
        }
        return array;
    }

    public static String[][] showAgreements(List<Bid> bids, Boolean manager) {
        if (bids == null) {
            return new String[][]{{null, null, null, null, null, null}};
        }
        List<Integer> index = getArrayForAgreements(bids);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][6];
        for (int i = 0; i < index.size(); i++) {
            Bid bid = bids.get(index.get(i));
            array[i][0] = Integer.toString(bid.getSum());
            array[i][1] = df.format(bid.getDate());
            array[i][2] = Integer.toString(bid.getResponseFinancier().getTime());
            array[i][3] = Integer.toString(bid.getResponseFinancier().getPersent());
            array[i][4] = Integer.toString(bid.getAgreement().getResidualAmount());
            if (manager) {
                array[i][5] = bid.getClient().getName();
            }
        }
        return array;
    }

    public static Bid getBidForAgreements(List<Bid> bids, int id) {
        List<Integer> index = getArrayForAgreements(bids);
        return bids.get(index.get(id));
    }

    public static List<Integer> getArrayForAgreements(List<Bid> bids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < bids.size(); i++) {
                if (bids.get(i).getAgreement() != null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }

    public static Bid getBidForApprovedBidsForManager(List<Bid> bids, int id) {
        List<Integer> index = getArrayForApprovedBidsForManager(bids);
        return bids.get(index.get(id));
    }

    public static List<Integer> getArrayForApprovedBidsForManager(List<Bid> bids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < bids.size(); i++) {
                if (bids.get(i).getResponseFinancier().getAnswer() && bids.get(i).getResponseClient() && bids.get(i).getAgreement() == null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }

    public static Bid getBidForApprovedBidsForClient(List<Bid> bids, int id) {
        List<Integer> index = getArrayForApprovedBidsForClient(bids);
        return bids.get(index.get(id));
    }

    public static List<Integer> getArrayForApprovedBidsForClient(List<Bid> bids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < bids.size(); i++) {
                if (bids.get(i).getResponseFinancier().getAnswer() != null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }

    public static Bid getBidForBidsClient(List<Bid> bids, int id) {
        List<Integer> index = getArrayForBidsClient(bids);
        return bids.get(index.get(id));
    }

    public static List<Integer> getArrayForBidsClient(List<Bid> bids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < bids.size(); i++) {
                if (bids.get(i).getResponseFinancier() == null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }

    public static Bid getBidForBidsManager(List<Bid> bids, int id) {
        List<Integer> index = getArrayForBidsManager(bids);
        return bids.get(index.get(id));
    }

    public static List<Integer> getArrayForBidsManager(List<Bid> bids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < bids.size(); i++) {
                if (bids.get(i).getResponseFinancier() == null || (bids.get(i).getResponseFinancier() != null && bids.get(i).getResponseFinancier().getAnswer() == null)) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }

    public static String[][] showBidsFinancier(List<Bid> bids) {
        if (bids == null) {
            return new String[][]{{null, null, null, null, null}};
        }
        List<Integer> index = getArrayForBidsFinancier(bids);

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][5];
        for (int i = 0; i < index.size(); i++) {
            Bid bid = bids.get(index.get(i));
            array[i][0] = bid.getClient().getName();
            array[i][1] = Integer.toString(bid.getClient().getRating());
            array[i][2] = Integer.toString(bid.getClient().getRevenue());
            array[i][3] = Integer.toString(bid.getSum());
            array[i][4] = df.format(bid.getDate());
        }
        return array;
    }

    public static Bid getBidForBidsFinancier(List<Bid> bids, int id) {
        List<Integer> index = getArrayForBidsFinancier(bids);
        return bids.get(index.get(id));
    }

    public static List<Integer> getArrayForBidsFinancier(List<Bid> bids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < bids.size(); i++) {
                if (bids.get(i).getResponseFinancier().getAnswer() == null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }
}
