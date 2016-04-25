#!/bin/sh -ex

#Use this command to view the command usages
#java -cp "target/classes:target/dependency/*" com.dk.flowershop.FlowerShopApplication new -help

java -cp "target/classes:target/dependency/*" com.dk.flowershop.FlowerShopApplication new -flowers R12:10 -flowers L09:15 -flowers T58:13
