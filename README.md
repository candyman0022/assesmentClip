# assesmentClip
test assesment project for a transaction application
run with assesment-1.0-SNAPSHOT-jar-with-dependencies.jar

**Usage:**

java -jar

./application <user_id> add <transaction_json>

./application <user_id> <transaction_id>

./application <user_id> list

./application <user_id> sum


<user_id> should be an integer



<transaction_json> needs to have it's double quotes escaped ej. "{ \\"amount\\": 1.23, \\"description\\": \\"Joes Tacos\\", \\"date\\":\\"2018-10-30\\", \\"user_id\\": 345 }"


**Example run**:

java -jar assesment-1.0-SNAPSHOT-jar-with-dependencies.jar 1337 add "{ \"amount\": 1.23, \"description\": \"Joes Tacos\", \"date\":\"2018-10-30\", \"user_id\": 345 }"

java -jar assesment-1.0-SNAPSHOT-jar-with-dependencies.jar 1337 b4c94bc2-c5fd-4f53-b628-be8e428aee07

java -jar assesment-1.0-SNAPSHOT-jar-with-dependencies.jar 1337 list

java -jar assesment-1.0-SNAPSHOT-jar-with-dependencies.jar 1337 sum
