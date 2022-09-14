import React, { useState } from "react";
import "../CSS/Summary.css";
import OverusedWordCard from "./OverusedWordCard";
import FillerCard from "./FillerCard";
import PaceCard from "./PaceCard";
import GrammarFeedback from "./GrammarFeedback";
import SummaryPageHeader from "./SummaryPageHeader";
import { Animated } from "react-animated-css";
import SentimentCard from "./SentimentCard";
import { useLocation } from 'react-router-dom';

function SummaryPage() {
    const location = useLocation();
	const { userID, name, data } = location.state;

    const incorrect_correct = {}
    const words = {};

    const [showAllGrammar, setViewAll] = useState(false);
    
    const didTapViewAll = (show) => {
        setViewAll(show)
    }

    return (
        <Animated animationIn="rotateIn" animationOut="rotateOut">  
            <div className="mainContainer">
                <div className="bgCard"> 
                    <SummaryPageHeader userID = {userID} name = {name} score = {data.score}/>
                    <div className="summary-container-child">
                    {(() => {
                            if (!showAllGrammar) {
                                return (
                                    <div className="summary-container-child-col">
                                        <OverusedWordCard overused = {words}/>
                                        <div className="summary-container-child-pace-filler-row">
                                            <FillerCard fillerCount={data.fillerRatio}/>
                                            <PaceCard pace = {data.speechRate}/>
                                        </div>
                                        <SentimentCard sentiment = {data.sentiment}/>
                                    </div>
                                );
                            }
                        }
                    )()}
                        <div className="summary-container-child-col">
                            <GrammarFeedback incorrect_correct ={incorrect_correct} showAllGrammar = {showAllGrammar}  didTapViewAll={didTapViewAll}/>
                        </div>
                    </div>
                </div>
            </div>
        </Animated>
    );
}


export default SummaryPage;