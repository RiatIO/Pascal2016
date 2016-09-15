i=$1

if [[ $i == "testscanner" ]]; then
    echo "Moving log files to tests/scanner_log"
    mkdir tests/scanner_log
    cp -f tests/*.log tests/scanner_log
    rm -f tests/*.log
fi
