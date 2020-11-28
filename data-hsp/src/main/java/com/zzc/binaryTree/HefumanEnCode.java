package com.zzc.binaryTree;

import java.io.*;
import java.util.*;

public class HefumanEnCode {

    static Map<Byte, String> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {
//        String sourceStr = "i like like like java do you like a java";
//        byte[] zip = zip(sourceStr);
//        zipFile("D:\\myself\\data-structure\\note\\1.txt");
        unZipFile("D:\\myself\\data-structure\\note\\1.zip");
    }


    public static void zipFile(String fileName){
        File file = new File(fileName);
        try(InputStream inputStream = new FileInputStream(file);
            FileOutputStream outputStream = new FileOutputStream("D:\\myself\\data-structure\\note\\1.zip");
            ObjectOutputStream objectOs = new ObjectOutputStream(outputStream)){
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            byte[] zip = zip(bytes);
            objectOs.writeObject(zip);
            objectOs.writeObject(huffmanCodes);
            objectOs.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void unZipFile(String fileName){
        try(FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream objectIs = new ObjectInputStream(fis);
            FileOutputStream fos = new FileOutputStream("D:\\myself\\data-structure\\note\\2.txt")){
            byte[] bytes = (byte[]) objectIs.readObject();
            huffmanCodes = (Map<Byte, String>) objectIs.readObject();
            byte[] unzip = unzip(bytes);
            fos.write(unzip);
            fos.flush();
            System.out.println("解压成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static byte[] zip(String sourceStr) {
       return zip(sourceStr.getBytes());
    }

    public static byte[] zip(byte[] bytes) {
        // 40个字节
        //105  32  108  105  107  101  32  108  105  107
        //101  32  108  105  107  101  32  106  97   118
        //97   32  100  111  32   121  111 117  32   108
        //105  107 101  32   97   32   106 97   118  97

//        for (byte b : bytes) {
//            System.out.print(b + "  ");
//        }
        //Node[data=32(代表上面的bytes),weight=9(代表32出现了9此)]
        List<Node> nodes = dealStrToBytes(bytes);
        //创建赫夫曼树
        Node root = createHefuManTree(nodes);
        //根据赫夫曼树创建编码 Map<Byte, String> huffmanCodes
        //32->01     97->100    100->11000  117->11001
        //101->1110  118->11011 105->101    121->11010
        //106->0010  107->1111  108->000    111->0011
        createHuffmanCodes(root, "", new StringBuilder());
        // 1. 生成压缩的二进制
        // 10101000 10111111 11001000 10111111 11001000
        // 10111111 11001001 01001101 11000111 00000110
        // 11101000 11110010 10001011 11111100 11000100
        // 10100110 11100
        // 2.将1的结果8位转成一个byte
        // -88  -65  -56  -65  -56
        // -65  -55  77  -57  6
        // -24  -14  -117  -4  -60
        // -90  28
        byte[] zip = zipUseHuffManCode(bytes);
        return zip;
    }

    public static byte[] unzip(byte[] zip) {
        //1.将zip的每个元素转成8位二进制的字符串
        StringBuilder zipSB = new StringBuilder();
        for (int i = 0; i < zip.length; i++) {
            // 当前仅当最后一位是正数时不需要补高位或者截取高位
            if (i == zip.length - 1 && zip[i] > 0) {
                zipSB.append(byteTo8BinaryString(zip[i], false));
            } else {
                zipSB.append(byteTo8BinaryString(zip[i], true));
            }
        }
//        System.out.println("1.解压的二进制=" + zipSB.toString());

        // 2.反转huffmanCodes的key和value用于解码1中的二进制
        Map<String, Byte> unZiphuffmanCodes = new HashMap<>();
        huffmanCodes.entrySet().forEach(entry -> {
            unZiphuffmanCodes.put(entry.getValue(), entry.getKey());
        });

        //3.解码
        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < zipSB.length(); i++) {
            int count = i;
            boolean findFlag = false;
            while (!findFlag) {
                if (unZiphuffmanCodes.containsKey(zipSB.substring(i, count))) {
                    byteList.add(unZiphuffmanCodes.get(zipSB.substring(i, count)));
                    findFlag = true;
                } else {
                    count++;
                }

            }
            i = count - 1;
        }

        byte[] unzip = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            unzip[i] = byteList.get(i);
        }
        return unzip;
    }

    /**
     * 将byte转为8位二进制的字符串
     *
     * @param flag 最高位是否需要补位
     */
    public static String byteTo8BinaryString(byte b, boolean flag) {
        int temp = b;
        // 这里输出的是补码 负数的补码=源码的反码+1，正数不变
        // TODO 负数输出32位，正数不变

        // 正数需要补位, 负数不需要但不影响值
        if (flag) {
            temp |= 256;
        }
        String binaryStr = Integer.toBinaryString(temp);
        if (flag) {
            binaryStr = binaryStr.substring(binaryStr.length() - 8);
        }
        return binaryStr;
    }

    /**
     * 处理字符串，将其解析成map，其中map的key代表字符，value代表者字符出现的数量
     */
    public static List<Node> dealStrToBytes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();

        Map<Byte, Integer> map = new HashMap<>();


        for (int i = 0; i < bytes.length; i++) {
            if (!map.containsKey(bytes[i])) {
                map.put(bytes[i], 1);
            } else {
                map.put(bytes[i], map.get(bytes[i]) + 1);
            }
        }

        Node temp;
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            temp = new Node(entry.getKey(), entry.getValue());
            nodes.add(temp);
        }

        return nodes;
    }

    /**
     * 生成赫夫曼树
     */
    public static Node createHefuManTree(List<Node> list) {
        Node temp;
        while (list.size() > 1) {
            // 1. 从小到大排序
            list.sort(Node::compareTo);
            // 2. 弹出两个元素组成二叉树
            temp = new Node(list.get(0).weight + list.get(1).weight);
            temp.leftNode = list.get(0);
            temp.rightNode = list.get(1);
            // 3. 删除弹出的两个头节点，并将父节点temp加入list
            list.remove(0);
            list.remove(0);
            list.add(temp);
        }
        return list.get(0);
    }

    /**
     * 生成赫夫曼编码
     */
    public static void createHuffmanCodes(Node node, String str, StringBuilder stringBuilder) {
        // 上个节点的编码
        StringBuilder s2 = new StringBuilder(stringBuilder);
        s2.append(str);
        if (node != null) {
            // 非叶子节点
            if (node.data == null) {
                // 向左子树遍历
                createHuffmanCodes(node.leftNode, "0", s2);
                // 向右子树遍历
                createHuffmanCodes(node.rightNode, "1", s2);
            } else {
                huffmanCodes.put(node.data, s2.toString());
            }
        }
    }

    /**
     * 压缩：
     * 注意：这里的子节代表的都是补码，解压时也要使用补码
     * 如 前8位 10101000 原码的byte = -40
     * 它的补码 11011000 byte = -88 我们存的就是88，
     */
    public static byte[] zipUseHuffManCode(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        int lenght = stringBuilder.length() / 8;
        lenght = stringBuilder.length() % 8 == 0 ? lenght : lenght + 1;
        byte[] codes = new byte[lenght];
        int i = 0;
        byte b;
        while (i < lenght) {
            if (i == lenght - 1) {
                b = (byte) Integer.parseInt(stringBuilder.substring(i * 8), 2);
            } else {
                b = (byte) Integer.parseInt(stringBuilder.substring(i * 8, (i + 1) * 8), 2);
            }
            codes[i++] = b;
        }
        return codes;
    }

    public static void printHuffmanCodes() {
        huffmanCodes.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "==" + entry.getValue());
        });
    }

    static class Node implements Comparable<Node> {
        Byte data;
        int weight; // 出现的次数
        Node leftNode;
        Node rightNode;

        public Node(int weight) {
            this.weight = weight;
        }

        public Node(byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
