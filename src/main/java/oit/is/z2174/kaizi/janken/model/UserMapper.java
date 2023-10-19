package oit.is.z2174.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * from users;")
  ArrayList<User> selectAllUsers();

  @Select("SELECT * from users where id = #{id}")
  User selectAllById(int id);

  /**
   * #{userName}などはinsertの引数にあるChamberクラスのフィールドを表しています 引数に直接String
   * userNameなどと書いてもいけるはず
   * 下記のOptionsを指定すると，insert実行時にAuto incrementされたIDの情報を取得できるようになる useGeneratedKeys
   * = true -> Keyは自動生成されることを表す keyColumn : keyになるテーブルのカラム名 keyProperty :
   * keyになるJavaクラスのフィールド名
   *
   * @param users
   */
  @Insert("INSERT INTO users (userName) VALUES (#{userName});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(User user);

  @Select("SELECT * from users where userName = #{userName}")
  ArrayList<User> selectAllByUserName(String userName);

  @Select("SELECT * from users where userName = #{userName}")
  User selectByUserName(String userName);

  /**
   * DBのカラム名とjavaクラスのフィールド名が同じ場合はそのまま代入してくれる（大文字小文字の違いは無視される）
   * カラム名とフィールド名が異なる場合の対応も可能だが，いきなり複雑になるので，selectで指定するテーブル中のカラム名とクラスのフィールド名は同一になるよう設計することが望ましい
   *
   * @return
   */
  // @Select("SELECT users.userName from users JOIN userinfo ON
  // user.userName=userinfo.userName;")
  // ArrayList<User> selectAllUser();

  // @Insert("INSERT INTO userinfo (userName) VALUES (#{userName});")
  // void insertUserInfo(User userinfo);

}
