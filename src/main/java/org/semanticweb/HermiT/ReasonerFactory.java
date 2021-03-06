package org.semanticweb.HermiT;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

public class ReasonerFactory
implements OWLReasonerFactory {
    public String getReasonerName() {
        return "HermiT";
    }

    public OWLReasoner createReasoner(OWLOntology ontology) {
        return this.createReasoner(ontology, this.getProtegeConfiguration(null));
    }

    public OWLReasoner createReasoner(OWLOntology ontology, OWLReasonerConfiguration config) {
        return this.createHermiTOWLReasoner(this.getProtegeConfiguration(config), ontology);
    }

    public OWLReasoner createNonBufferingReasoner(OWLOntology ontology) {
        return this.createNonBufferingReasoner(ontology, this.getProtegeConfiguration(null));
    }

    public OWLReasoner createNonBufferingReasoner(OWLOntology ontology, OWLReasonerConfiguration owlAPIConfiguration) {
        Configuration configuration = this.getProtegeConfiguration(owlAPIConfiguration);
        configuration.bufferChanges = false;
        return this.createHermiTOWLReasoner(configuration, ontology);
    }

    protected Configuration getProtegeConfiguration(OWLReasonerConfiguration owlAPIConfiguration) {
        Configuration configuration;
        if (owlAPIConfiguration != null) {
            if (owlAPIConfiguration instanceof Configuration) {
                configuration = (Configuration)owlAPIConfiguration;
            } else {
                configuration = new Configuration();
                configuration.freshEntityPolicy = owlAPIConfiguration.getFreshEntityPolicy();
                configuration.individualNodeSetPolicy = owlAPIConfiguration.getIndividualNodeSetPolicy();
                configuration.reasonerProgressMonitor = owlAPIConfiguration.getProgressMonitor();
                configuration.individualTaskTimeout = owlAPIConfiguration.getTimeOut();
            }
        } else {
            configuration = new Configuration();
            configuration.ignoreUnsupportedDatatypes = true;
        }
        return configuration;
    }

    protected OWLReasoner createHermiTOWLReasoner(Configuration configuration, OWLOntology ontology) {
        return new Reasoner(configuration, ontology);
    }
}

