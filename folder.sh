# Script made to move log files into respestive folder
i=$1

printf "\nMoving log files to tests/${i}_log\n"

mkdir tests/${i}_log
mv tests/*.log tests/${i}_log
