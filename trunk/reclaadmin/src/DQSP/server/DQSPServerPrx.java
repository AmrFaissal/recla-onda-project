// **********************************************************************
//
// Copyright (c) 2003-2009 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.3.1

package DQSP.server;

public interface DQSPServerPrx extends Ice.ObjectPrx
{
    public java.util.List<AnalyseTable> getAllAnalysisTable();
    public java.util.List<AnalyseTable> getAllAnalysisTable(java.util.Map<String, String> __ctx);

    public java.util.List<Reclamation> getAllReclamations();
    public java.util.List<Reclamation> getAllReclamations(java.util.Map<String, String> __ctx);

    public java.util.List<AnalyseTable> getReclamationsSortedList();
    public java.util.List<AnalyseTable> getReclamationsSortedList(java.util.Map<String, String> __ctx);

    public java.util.List<VPassager> getMailingList();
    public java.util.List<VPassager> getMailingList(java.util.Map<String, String> __ctx);

    public java.util.List<VService> getAllServices();
    public java.util.List<VService> getAllServices(java.util.Map<String, String> __ctx);

    public void updateAnalyseTable(String theme, String action);
    public void updateAnalyseTable(String theme, String action, java.util.Map<String, String> __ctx);

    public void updateActionsEntreprises(String theme, boolean validation);
    public void updateActionsEntreprises(String theme, boolean validation, java.util.Map<String, String> __ctx);

    public void addPassager(int idPassager, String sex, String nom, String mail, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String prov, String dest, String nationalite);
    public void addPassager(int idPassager, String sex, String nom, String mail, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String prov, String dest, String nationalite, java.util.Map<String, String> __ctx);

    public void addReclamation(int idPassager, String nomAeroport, String terminale, String nomService, String remarque, String descriptif);
    public void addReclamation(int idPassager, String nomAeroport, String terminale, String nomService, String remarque, String descriptif, java.util.Map<String, String> __ctx);
}
