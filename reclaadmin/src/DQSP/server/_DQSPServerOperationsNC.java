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

public interface _DQSPServerOperationsNC
{
    java.util.List<AnalyseTable> getAllAnalysisTable();

    java.util.List<Reclamation> getAllReclamations();

    java.util.List<AnalyseTable> getReclamationsSortedList();

    java.util.List<VPassager> getMailingList();

    java.util.List<VService> getAllServices();

    void updateAnalyseTable(String theme, String action);

    void updateActionsEntreprises(String theme, boolean validation);

    void addPassager(int idPassager, String sex, String nom, String mail, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String prov, String dest, String nationalite);

    void addReclamation(int idPassager, String nomAeroport, String terminale, String nomService, String remarque, String descriptif);
}
