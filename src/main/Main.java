package main;

import GameTheory.Strategies.*;
import GameTheory.Tournament;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Coca-Cola Strategies
        Strategy cocaCola_HealthierProducts = new CelebrityEndorsements("Coca-Cola Healthier Products");
        Strategy cocaCola_DigitalMarketing = new DigitalMarketing("Coca-Cola Digital Marketing");
        Strategy cocaCola_Sustainability = new Sustainability("Coca-Cola Sustainability");
        Strategy cocaCola_BrandLoyalty = new BrandLoyalty("Coca-Cola Brand Loyalty");
        Strategy cocaCola_InfluencerMarketing = new InfluencerMarketing("Coca-Cola Influencer Marketing");
        Strategy cocaCola_LimitedTimeOffers = new LimitedTimeOffers("Coca-Cola Limited-Time Offers");

        // PepsiCo Strategies
        Strategy pepsiCo_CelebrityEndorsements = new CelebrityEndorsements("PepsiCo Celebrity Endorsements");
        Strategy pepsiCo_PersonalizedMarketing = new DigitalMarketing("PepsiCo Personalized Marketing");
        Strategy pepsiCo_Sponsorships = new InfluencerMarketing("PepsiCo Sponsorships");
        Strategy pepsiCo_DigitalEngagement = new LimitedTimeOffers("PepsiCo Digital Engagement");
        Strategy pepsiCo_LimitedTimeOffers = new Sustainability("PepsiCo Limited-Time Offers");
        Strategy pepsiCo_BrandLoyalty = new BrandLoyalty("PepsiCo Brand Loyalty");

        // Combine strategies from both companies
        List<Strategy> allStrategies = Arrays.asList(
                cocaCola_HealthierProducts, cocaCola_DigitalMarketing, cocaCola_Sustainability,
                cocaCola_BrandLoyalty, cocaCola_InfluencerMarketing, cocaCola_LimitedTimeOffers,
                pepsiCo_CelebrityEndorsements, pepsiCo_PersonalizedMarketing, pepsiCo_Sponsorships,
                pepsiCo_DigitalEngagement, pepsiCo_LimitedTimeOffers, pepsiCo_BrandLoyalty
        );

        // Tournament Simulation
        Tournament tournament = new Tournament(allStrategies);
        int numRounds = 10;
        Map<Strategy, Integer> results = tournament.executeTournamentRounds(numRounds);

        // Display Results
        System.out.println("Marketing Strategy Simulation Results:");
        for (Map.Entry<Strategy, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey().getStrategyName() + ": " + entry.getValue() + " points");
        }
    }
}
