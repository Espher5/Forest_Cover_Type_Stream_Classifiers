| Students/Group | Project Type | Predictive Models | Dataset | Folder Name |
|:--------------:|:------------:|:-----------------:|---------|-------------|
|  Michelangelo Esposito              |      Forest Cover Type        |  <h3>Online learning:</h3><ul><li>- [x] Adaptive Random Forest</li><li>- [x] Hoeffding Tree   </li><li>- [x] Naive Bayes</li></ul><h3>Batch learning:</h3><ul><li>- [x] Random Forest</li><li>- [x] Decision Tree   </li><li>- [x] Naive Bayes</li></ul> |   [Link](https://archive.ics.uci.edu/ml/datasets/covertype)            |     ./Michelangelo-Esposito-Forest-CoverType        |
|                |              |                   |         |             |

# Forest Cover Type dataset analysis

## Summary
[- Context of the project](#Context-of-the-project): overview on the project's domain.\
[- Dataset analysis](#Dataset-analysis): system architecture definition.\
[- Installation](#Installation): instructions to run the system from scratch.

This project contains an analysis of UCI's Forest Cover Type dataset and was developed for the "IoT Data Analytics" university course.  The dataset is available at the following [link](https://archive.ics.uci.edu/ml/datasets/covertype).

# Context-of-the-project
The main focus of this project is to employ **online machine learning** techniques to analyse the performances of various classifiers on a data stream. This stream does not produce real-time data, but it is generated from a csv/arff file in order to use the online approach.
The platform chosen for the initial analysis is **MOA, Massive Online Analysis**; the three classifiers employed during this phase are:
- **Adaptive Random Forest**.
- **Hoeffding Tree**.
- **Naive Bayes**.

At this point, a first comparison among the three classifiers was made, in order to analyse their performances and costs measured.
Then, the **Scikit Multiflow** library in Python was used to repeat the saem online lerarning process, in order to further elaborate on MOA's efficieny and also obtain some kind of comparison in terms of accuracy, execution times, etc.
Finally, a traditional batch approach was employed to additionally assess the potential gap with online learning. The chosen library is **Scikit Learn** in Python, The three batch classifiers used are:
- **Random Forest**.
- **Decision Tree**.
- **Naive Bayes**.

# Dataset-analysis
The dataset used for this study is available for free on the UCI Machine Learning Repository website and its employment revolves around predicting forest cover type from cartographic variables only; no images or other kinds of data are present in this dataset. The actual forest cover type for a given observation (30 x 30 meters cell) was determined from US Forest Service (USFS) Region 2 Resource Information System (RIS) data. Independent variables were derived from data originally obtained from US Geological Survey (USGS) and USFS data. The data was not originally scaled and contains binary columns of data for qualitative independent variables, such as wilderness areas and soil types. A total of over 580,000 entries are present in the dataset, each with the following attributes:
- Elevation in meters.
- Aspect in degrees azimuth.
- Slope in degrees.
- Horizontal distance to nearest surface water.
- Vertical distance to nearest surface water.
- Horizontal distance to nearest roadway.
- Hill shade index at 9am, summer solstice.
- Hill shade index at noon, summer solstice.
- Hill shade index at 3am, simmer solstice.
- Horizontal distance to nearest wildfire ignition points.
- Wilderness area designation.
- Soil type designation.
- Forest cover type designation (dependent attribute). The seven cover types, with the respective number of entries, are: 
    - Spruce/Fir: 211840.
    - Lodgepole Pine: 283301.
    - Ponderosa Pine: 35754.
    - Cottonwood/Willow: 2747.
    - Aspen: 9493.
    - Douglas-fir: 17367.
    - Krummholz: 20510.


Before employing classification techniques on the dataset, its composition was analyzed, as well as the distribution of the various features, and its values.
**Note** that, for the online classification part, no balancing or optimization techniques were considered, since it was assumed that the entries from the dataset were being streamed in real time from a source of some kind, such as iot sensors. On the other hand, for the batch learning approach we aimed at obtaining the best results by applying various pre-processing techniques.

First of all, the distribution of the various values was analyed. It was immediately noticeable that there was a large imbalance among the seven cover types. The respective percentages are the following:
- Lodgepole Pine: 48.76%
- Spruce/Fir: 36.46%
- Ponderosa Pine: 6.15%
- Krummholz: 3.53%
- Douglas-fir: 2.99%
- Aspen: 1.63%
- Cottonwood/Willow: 0.47%


The following preprocessing steps have been performed on the dataset:
- Distribution analysis.
- Skewness analysis.
- Correlation analysis.
