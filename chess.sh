#! /bin/sh
p=$(pwd)
osascript -e 'tell app "Terminal"
    do script "cd '$p'; java -jar neochess.phase0.server/build/libs/neochess.phase0.server-0.1.jar "
end tell'
osascript -e 'tell app "Terminal"
    do script "cd '$p'; java -jar neochess.phase0.client/build/libs/neochess.phase0.client-0.1.jar "
end tell'
osascript -e 'tell app "Terminal"
    do script "cd '$p'; java -jar neochess.phase0.client/build/libs/neochess.phase0.client-0.1.jar "
end tell'