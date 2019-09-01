package org.bibalex.eol.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bibalex.eol.mysql.MysqlService;
import org.bibalex.eol.mysqlModels.*;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class MysqlHandler {

    private EntityManager entityManager;
    private static Logger logger = LogManager.getLogger(MysqlHandler.class);
    private MysqlService mysqlService = new MysqlService();

    public MysqlHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public ArrayList<MysqlRank> getRanks(Date startDate, Date endDate) {

        StoredProcedureQuery getRanks = entityManager
                .createStoredProcedureQuery("getRanks")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getRanks.setParameter("start_date", startDate);
        getRanks.setParameter("end_date", endDate);

        List<Object[]> res = getRanks.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlRank> ranks = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlRank rank = new MysqlRank((BigInteger) line[0], (String) line[1]);
            ranks.add(rank);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Ranks: ");
        for (int i = 0; i < ranks.size(); i++)
            logger.debug(ranks.get(i).getName());
        return ranks;

    }

    public ArrayList<MysqlNode> getNodes(Date startDate, Date endDate) {

        StoredProcedureQuery getNodes = entityManager
                .createStoredProcedureQuery("getNodes")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getNodes.setParameter("start_date", startDate);
        getNodes.setParameter("end_date", endDate);

        List<Object[]> res = getNodes.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlNode> nodes = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlNode node = new MysqlNode((BigInteger) line[0], (Integer) line[1], (String) line[2], (String) line[3], (Integer) line[4], (String) line[5], (Integer) line[6], (BigInteger) line[7]);
            nodes.add(node);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Nodes: ");
        for (int i = 0; i < nodes.size(); i++)
            logger.debug(nodes.get(i).getGenerated_node_id());
        return nodes;
    }

    public ArrayList<MysqlPage> getPages(Date startDate, Date endDate) {

        StoredProcedureQuery getPages = entityManager
                .createStoredProcedureQuery("getPages")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getPages.setParameter("start_date", startDate);
        getPages.setParameter("end_date", endDate);

        List<Object[]> res = getPages.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlPage> pages = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlPage page = new MysqlPage((Integer) line[0], (Integer) line[1], (BigInteger) line[2]);
            pages.add(page);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Pages: ");
        for (int i = 0; i < pages.size(); i++)
            logger.debug(pages.get(i).getId());
        return pages;
    }

    public ArrayList<MysqlPagesNode> getPagesNodes(Date startDate, Date endDate) {

        StoredProcedureQuery getPagesNodes = entityManager
                .createStoredProcedureQuery("getPagesNodes")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getPagesNodes.setParameter("start_date", startDate);
        getPagesNodes.setParameter("end_date", endDate);

        List<Object[]> res = getPagesNodes.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlPagesNode> pages_nodes = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlPagesNode pages_node = new MysqlPagesNode((BigInteger) line[0], (BigInteger) line[1], (BigInteger) line[2], (Boolean) line[3]);
            pages_nodes.add(pages_node);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Pages Nodes: ");
        for (int i = 0; i < pages_nodes.size(); i++)
            logger.debug("Page ID: " + pages_nodes.get(i).getPage_id() + ", Node ID: " + pages_nodes.get(i).getNode_id());
        return pages_nodes;
    }

    public ArrayList<MysqlScientificName> getScientificNames(Date startDate, Date endDate) {

        StoredProcedureQuery getScientificNames = entityManager
                .createStoredProcedureQuery("getScientificNames")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getScientificNames.setParameter("start_date", startDate);
        getScientificNames.setParameter("end_date", endDate);

        List<Object[]> res = getScientificNames.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlScientificName> scientific_names = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlScientificName scientific_name = new MysqlScientificName((BigInteger) line[0], (Integer) line[1], (String) line[2], (String) line[3], (String) line[4], (Integer) line[5], (Boolean) line[6], (BigInteger) line[7], (BigInteger) line[8], (BigInteger) line[9]);
            scientific_names.add(scientific_name);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Scientific Names: ");
        for (int i = 0; i < scientific_names.size(); i++)
            logger.debug(scientific_names.get(i).getCanonical_form());
        return scientific_names;
    }

    public ArrayList<MysqlLanguage> getLanguages(Date startDate, Date endDate) {

        StoredProcedureQuery getLanguages = entityManager
                .createStoredProcedureQuery("getLanguages")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getLanguages.setParameter("start_date", startDate);
        getLanguages.setParameter("end_date", endDate);

        List<Object[]> res = getLanguages.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlLanguage> languages = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlLanguage language = new MysqlLanguage((BigInteger) line[0], (String) line[1], (String) line[2]);
            languages.add(language);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Languages: ");
        for (int i = 0; i < languages.size(); i++)
            logger.debug(languages.get(i).getCode());
        return languages;
    }

    public ArrayList<MysqlVernacular> getVernaculars(Date startDate, Date endDate) {

        StoredProcedureQuery getVernaculars = entityManager
                .createStoredProcedureQuery("getVernaculars")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getVernaculars.setParameter("start_date", startDate);
        getVernaculars.setParameter("end_date", endDate);

        List<Object[]> res = getVernaculars.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlVernacular> vernaculars = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlVernacular vernacular = new MysqlVernacular((BigInteger) line[0], (String) line[1], (Integer) line[2], (Boolean) line[3], (Integer) line[4], (BigInteger) line[5], (BigInteger) line[6], (BigInteger) line[7]);
            vernaculars.add(vernacular);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Vernaculars: ");
        for (int i = 0; i < vernaculars.size(); i++)
            logger.debug(vernaculars.get(i).getString());
        return vernaculars;
    }

    public ArrayList<MysqlLicense> getLicenses(Date startDate, Date endDate) {

        StoredProcedureQuery getLicenses = entityManager
                .createStoredProcedureQuery("getLicenses")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getLicenses.setParameter("start_date", startDate);
        getLicenses.setParameter("end_date", endDate);

        List<Object[]> res = getLicenses.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlLicense> licenses = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlLicense license = new MysqlLicense((BigInteger) line[0], (String) line[1], (String) line[2]);
            licenses.add(license);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Licenses: ");
        for (int i = 0; i < licenses.size(); i++)
            logger.debug(licenses.get(i).getName());
        return licenses;
    }

    public ArrayList<MysqlLocation> getLocations(Date startDate, Date endDate) {

        StoredProcedureQuery getLocations = entityManager
                .createStoredProcedureQuery("getLocations")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getLocations.setParameter("start_date", startDate);
        getLocations.setParameter("end_date", endDate);

        List<Object[]> res = getLocations.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlLocation> locations = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlLocation location = new MysqlLocation((BigInteger) line[0], (Integer) line[1], (String) line[2], (float) line[3], (float) line[4], (float) line[5], (String) line[6]);
            locations.add(location);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Locations: ");
        for (int i = 0; i < locations.size(); i++)
            logger.debug(locations.get(i).getId());
        return locations;
    }

    public ArrayList<MysqlMedium> getMedia(Date startDate, Date endDate) {

        StoredProcedureQuery getMedia = entityManager
                .createStoredProcedureQuery("getMedia")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getMedia.setParameter("start_date", startDate);
        getMedia.setParameter("end_date", endDate);

        List<Object[]> res = getMedia.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlMedium> media = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlMedium location = new MysqlMedium((BigInteger) line[0], (Integer) line[1], (String) line[2], (String) line[3], (Integer) line[4], (String) line[5], (String) line[6], (String) line[7], (String) line[8],
                    (Integer) line[9], (Integer) line[10], (String) line[11], (String) line[12], (String) line[13], (BigInteger) line[14],
                    (BigInteger) line[15], (BigInteger) line[16], (BigInteger) line[17]);
            media.add(location);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Media: ");
        for (int i = 0; i < media.size(); i++)
            logger.debug(media.get(i).getBase_url());
        return media;
    }

    public ArrayList<MysqlArticle> getArticles(Date startDate, Date endDate) {

        StoredProcedureQuery getArticles = entityManager
                .createStoredProcedureQuery("getArticles")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getArticles.setParameter("start_date", startDate);
        getArticles.setParameter("end_date", endDate);

        List<Object[]> res = getArticles.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlArticle> articles = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlArticle location = new MysqlArticle((BigInteger) line[0], (String) line[1], (Integer) line[2], (String) line[3],
                    (String) line[4], (Integer) line[5], (String) line[6], (String) line[7], (String) line[8],
                    (BigInteger) line[9], (BigInteger) line[10], (BigInteger) line[11], (BigInteger) line[12], (String) line[13]);
            articles.add(location);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Articles: ");
        for (int i = 0; i < articles.size(); i++)
            logger.debug(articles.get(i).getName());
        return articles;
    }

    public ArrayList<MysqlPageContent> getPageContents(Date startDate, Date endDate) {

        StoredProcedureQuery getPageContents = entityManager
                .createStoredProcedureQuery("getPageContents")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getPageContents.setParameter("start_date", startDate);
        getPageContents.setParameter("end_date", endDate);

        List<Object[]> res = getPageContents.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlPageContent> page_contents = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlPageContent page_content = new MysqlPageContent((BigInteger) line[0], (Integer) line[1], (String) line[2], (Integer) line[3], (Integer) line[4], (Boolean) line[5], (Boolean) line[6],
                    (Boolean) line[7], (Boolean) line[8], (BigInteger) line[9], (Integer) line[10]);
            page_contents.add(page_content);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Pages Contents: ");
        for (int i = 0; i < page_contents.size(); i++)
            logger.debug("Page ID: " + page_contents.get(i).getPage_id() + ", Content ID: " + page_contents.get(i).getContent_id());
        return page_contents;
    }

    public ArrayList<MysqlAgent> getAgents(Date startDate, Date endDate) {

        StoredProcedureQuery getAgents = entityManager
                .createStoredProcedureQuery("getAgents")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getAgents.setParameter("start_date", startDate);
        getAgents.setParameter("end_date", endDate);

        List<Object[]> res = getAgents.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlAgent> agents = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlAgent agent = new MysqlAgent((BigInteger) line[0], (Integer) line[1], (String) line[2], (String) line[3], (String) line[4], (String) line[5], (Integer) line[6], (String) line[7], (String) line[8]);
            agents.add(agent);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Agents: ");
        for (int i = 0; i < agents.size(); i++)
            logger.debug(agents.get(i).getId());
        return agents;
    }

    public ArrayList<MysqlReferent> getReferents(Date startDate, Date endDate) {

        StoredProcedureQuery getReferents = entityManager
                .createStoredProcedureQuery("getReferents")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getReferents.setParameter("start_date", startDate);
        getReferents.setParameter("end_date", endDate);

        List<Object[]> res = getReferents.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlReferent> referents = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlReferent referent = new MysqlReferent((BigInteger) line[0], (String) line[1], (String) line[2], (Integer) line[3], (Integer) line[4], (Integer) line[5], (String) line[6], (String) line[7], (String) line[8],
                    (String) line[9], (String) line[10], (Date) line[11], (String) line[12], (String) line[13], (Integer) line[14], (String) line[15]);
            referents.add(referent);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Referents: ");
        for (int i = 0; i < referents.size(); i++)
            logger.debug(referents.get(i).getId());
        return referents;
    }

    public ArrayList<MysqlReference> getReferences(Date startDate, Date endDate) {

        StoredProcedureQuery getReferences = entityManager
                .createStoredProcedureQuery("getReferences")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getReferences.setParameter("start_date", startDate);
        getReferences.setParameter("end_date", endDate);

        List<Object[]> res = getReferences.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlReference> references = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlReference reference = new MysqlReference((BigInteger) line[0], (Integer) line[1], (String) line[2], (Integer) line[3], (Integer) line[4]);
            references.add(reference);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("References: ");
        for (int i = 0; i < references.size(); i++)
            logger.debug(references.get(i).getId());
        return references;
    }


    public ArrayList<MysqlTrait> getTraits(Date startDate, Date endDate) {

        StoredProcedureQuery getTraits = entityManager
                .createStoredProcedureQuery("getTraits")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getTraits.setParameter("start_date", startDate);
        getTraits.setParameter("end_date", endDate);

        List<Object[]> res = getTraits.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlTrait> traits = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlTrait trait = new MysqlTrait((int) line[0], (String) line[1], (String) line[2], (String) line[3], (String) line[4]);
            traits.add(trait);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("The Following Nodes Have Traits: ");
        for (int i = 0; i < traits.size(); i++)
            logger.debug(traits.get(i).getGenerated_node_id());
        return traits;

    }

    public ArrayList<MysqlTaxon> getTaxa(Date startDate, Date endDate) {

        StoredProcedureQuery getTaxa = entityManager
                .createStoredProcedureQuery("getTaxa")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getTaxa.setParameter("start_date", startDate);
        getTaxa.setParameter("end_date", endDate);

        List<Object[]> res = getTaxa.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlTaxon> taxa = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlTaxon taxon = new MysqlTaxon((Integer) line[0], (Integer) line[1], (String) line[2], (String) line[3], (String) line[4], (String) line[5], (Integer) line[6]);
            taxa.add(taxon);
        }
        logger.debug("Start Date: " + startDate);
        logger.debug("End Date: " + endDate);
        logger.debug("Taxa: ");
        for (int i = 0; i < taxa.size(); i++)
            logger.debug(taxa.get(i).getScientific_name());
        return taxa;

    }

    public Date getEndTime() {
        StoredProcedureQuery getEndTime = entityManager
                .createStoredProcedureQuery("getEndTime")
                .registerStoredProcedureParameter(
                        "end_time", Date.class, ParameterMode.OUT);

        getEndTime.execute();
        return (Date) getEndTime.getOutputParameterValue("end_time");
    }

    public void updateHarvestTime() {
        StoredProcedureQuery updateHarvestTime = entityManager
                .createStoredProcedureQuery("updateHarvestTime")
                .registerStoredProcedureParameter(
                        "harvest_time", Date.class, ParameterMode.IN);
        updateHarvestTime.setParameter("harvest_time", new Date());
        updateHarvestTime.execute();
    }

    public void loadRanks() {
        logger.debug("Loading Ranks");

        PrintWriter writer = null;
        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();
            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "ranks.txt" +
                    "' ignore into table ranks FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(name,created_at,updated_at);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();
//makes file empty
            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "ranks.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }

    }

    public void loadNodes() {
        logger.debug("Loading Nodes");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();
            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "nodes.txt" +
                    "'ignore into table nodes FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(resource_id,scientific_name,canonical_form,generated_node_id,resource_pk,created_at,updated_at,@column10)" +
                    "set rank_id = (SELECT id FROM ranks WHERE name = @column10);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "nodes.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadPages() {
        logger.debug("Loading Pages");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();
            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();
            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "pages.txt" +
                    "'ignore into table pages FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(id,created_at,updated_at,node_id);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            Query update_query = entityManager.createNativeQuery("update pages set updated=true, node_id = (select id from nodes where generated_node_id=pages.node_id) where updated=false;");
            update_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "pages.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadPagesNodes() {
        logger.debug("Loading Pages Nodes");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "pages_nodes.txt" +
                    "'ignore into table pages_nodes FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(page_id,is_native,created_at,updated_at,node_id);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            Query update_query = entityManager.createNativeQuery("update pages_nodes set updated=true, node_id = (select id from nodes where generated_node_id=pages_nodes.node_id) where updated=false;");
            update_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "pages_nodes.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadScientificNames() {
        logger.debug("Loading Scientific Names");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "scientific_names.txt" +
                    "'ignore into table scientific_names FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(resource_id,canonical_form,italicized,node_resource_pk,generated_node_id,page_id,taxonomic_status_id,created_at,updated_at,node_id);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            Query update_query = entityManager.createNativeQuery("update scientific_names set updated=true, node_id = (select id from nodes where generated_node_id=scientific_names.node_id) where updated=false;");
            update_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "scientific_names.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadLanguages() {
        logger.debug("Loading Languages");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "languages.txt" + "' ignore into table languages FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(code,`group`,created_at,updated_at);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "languages.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadVernaculars() {
        logger.debug("Loading Vernaculars");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "vernaculars.txt" +
                    "' ignore into table vernaculars FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(string,resource_id,is_prefered_by_resource,generated_node_id,page_id,created_at,updated_at,@column8,node_id)" +
                    "set language_id = (SELECT id FROM languages WHERE code = @column8);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            Query update_query = entityManager.createNativeQuery("update vernaculars set updated=true, node_id = (select id from nodes where generated_node_id=vernaculars.node_id) where updated=false;");
            update_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "vernaculars.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadLicenses() {
        logger.debug("Loading Licenses");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "licenses.txt" + "' ignore into table licenses FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(source_url,name,created_at,updated_at);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "licenses.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadLocations() {
        logger.debug("Loading Locations");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "locations.txt" +
                    "' ignore into table locations FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(resource_id,location,longitude,latitude,altitude,spatial_location,created_at,updated_at)");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "locations.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadMedia() {
        logger.debug("Loading Media");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "media.txt" +
                    "' ignore into table media FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(format,description,owner,resource_id,guid,resource_pk,source_page_url,base_url,subclass,name,created_at,updated_at,@column8,@column9)" +
                    "set license_id = (SELECT id FROM licenses WHERE source_url = @column9)," +
                    " language_id = (SELECT id FROM languages WHERE code = @column8);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "media.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadArticles() {
        logger.debug("Loading Articles");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "articles.txt" +
                    "' ignore into table articles FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(owner,resource_id,guid,resource_pk,name,created_at,updated_at,@column8,@column9,body)" +
                    "set license_id = (SELECT id FROM licenses WHERE source_url = @column9)," +
                    " language_id = (SELECT id FROM languages WHERE code = @column8);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "articles.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadPageContents() {
        logger.debug("Loading Page Contents");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "page_contents.txt" +
                    "' ignore into table page_contents FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(resource_id,page_id,source_page_id,content_type,created_at,updated_at,guid);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            Query update_query = entityManager.createNativeQuery("update page_contents set content_id = (select id from media where guid=page_contents.guid) where content_id IS NULL;");
            update_query.executeUpdate();

            Query update_query1 = entityManager.createNativeQuery("update page_contents set content_id = (select id from articles where guid=page_contents.guid) where content_id IS NULL;");
            update_query1.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "page_contents.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadAgents() {
        logger.debug("Loading Agents");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "agents.txt" +
                    "' ignore into table attributions FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(resource_id,content_type,role_name,url,resource_pk,value,content_resource_fk,created_at,updated_at,guid);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();


            Query update_query = entityManager.createNativeQuery("update attributions set content_id = (select id from media where guid=attributions.guid) where content_id IS NULL;");
            update_query.executeUpdate();

            Query update_query1 = entityManager.createNativeQuery("update attributions set content_id = (select id from articles where guid=attributions.guid) where content_id IS NULL;");
            update_query1.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "agents.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadReferents() {
        logger.debug("Loading Referents");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "referents.txt" + "' ignore into table referents FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(resource_id,body,created_at,updated_at);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "referents.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadReferences() {
        logger.debug("Loading References");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();
            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "references.txt" +
                    "' ignore into table `references` FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(resource_id,parent_type,created_at,updated_at,guid,@column6,@column7)" +
                    " set referent_id = (select id from referents where body=@column6 and resource_id=@column7);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            Query update_query = entityManager.createNativeQuery("update `references` set parent_id = (select id from media where guid=`references`.guid) where parent_id IS NULL;");
            update_query.executeUpdate();

            Query update_query1 = entityManager.createNativeQuery("update `references` set parent_id = (select id from articles where guid=`references`.guid) where parent_id IS NULL;");
            update_query1.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "references.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadTraits() {
        logger.debug("Loading Traits");
        PrintWriter writer = null;
        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();
            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "traits.txt" +
                    "' ignore into table traits FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(generated_node_id,occurrences,associations,measurement_or_facts,target_occurrences,created_at,updated_at);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();
//makes file empty
            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "traits.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void loadTaxa() {
        logger.debug("Loading Taxa");
        PrintWriter writer = null;

        try {
            entityManager.joinTransaction();

            Query transaction_query = entityManager.createNativeQuery("START TRANSACTION;");
            transaction_query.executeUpdate();

            Query query = entityManager.createNativeQuery("LOAD DATA INFILE '" + PropertiesHandler.getProperty("mysqlFiles") + "taxa.txt" +
                    "' ignore into table taxa FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'" +
                    "(generated_node_id,page_eol_id,scientific_name,dataset_id,source,resource_id,occurrences,created_at,updated_at);");
            query.executeUpdate();

            Query commit_query = entityManager.createNativeQuery("commit;");
            commit_query.executeUpdate();

            writer = new PrintWriter(PropertiesHandler.getProperty("mysqlFiles") + "taxa.txt");
            writer.print("");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException:\n", e);
        } catch (Exception e) {
            logger.error("Exception:\n", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public HashMap<String, String> getCounts(Long resourceID) {
        HashMap<String, String> resourceCounts = new HashMap<>();

        String nodesCount = executeQuery("nodes", resourceID);
        resourceCounts.put("nodes", nodesCount);

        String sciNamesCount = executeQuery("scientific_names", resourceID);
        resourceCounts.put("scientificNames", sciNamesCount);

        String vernacularsCount = executeQuery("vernaculars", resourceID);
        resourceCounts.put("vernaculars", vernacularsCount);

        String mediaCount = executeQuery("media", resourceID);
        resourceCounts.put("media", mediaCount);

        String articlesCount = executeQuery("articles", resourceID);
        resourceCounts.put("articles", articlesCount);

        String referencesCount = executeQuery("`references`", resourceID);
        resourceCounts.put("references", referencesCount);

        String occurrencesCount = executeJoinQuery("occurrences", resourceID);
        resourceCounts.put("occurrences", occurrencesCount);

        String associationsCount = executeJoinQuery("associations", resourceID);
        resourceCounts.put("associations", associationsCount);

        String measurementsCount = executeJoinQuery("measurement_or_facts", resourceID);
        resourceCounts.put("measurementsOrFacts", measurementsCount);

        return resourceCounts;
    }

    private String executeJoinQuery(String traits, Long resourceID) {
        Query countQuery = entityManager.createNativeQuery("select count(*) from traits t inner join nodes n on t.generated_node_id = n.generated_node_id where n.resource_id = "
                + resourceID + " and " + traits + " is not null and " + traits + " != \' \';");
        return countQuery.getSingleResult().toString();
    }

    private String executeQuery(String tableName, Long resourceID) {
        Query countQuery = entityManager.createNativeQuery("select count(*) from " + tableName + " where resource_id = " + resourceID + ";");
        return countQuery.getSingleResult().toString();
    }

}