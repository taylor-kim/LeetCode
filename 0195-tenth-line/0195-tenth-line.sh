cnt=0
while read line && [ $cnt -le 10 ]; 
do 
    let 'cnt = cnt + 1' # cnt=$((cnt + 1))
    if [ $cnt -eq 10 ]; then
        echo $line
        exit 0
    fi
done < file.txt
# cnt=0 “初始化计数器变量 cnt 为 0，用于记录读取的行数”
# while read line && [ $cnt -le 10 ]; do “每次读一行，并且检查计数器 cnt 是否小于等于 10”
# let 'cnt = cnt + 1' “使用 let 进行数学运算，将计数器变量 cnt 加 1”
# if [ $cnt -eq 10 ]; then “判断当前计数器值是否等于 10”
# echo $line “打印第 10 行”
# exit 0 “输出第 10 行后，退出脚本，状态码为 0 表示成功”
