HealthCareSystem
 概要

   Java Servlet,JSP,H2 Databaseを用いて開発した健康管理システムです。

   ユーザーが日々の体温や睡眠時間を記録し、健康状態を確認できるWebアプリケーションです。

 使用技術

  Java
  Servlet
  JSP
  H2 Database
  Apache Tomcat 9
  Eclipse

 主な機能

  ユーザー登録
  ログイン機能
  健康記録登録
  健康記録一覧表示
  健康記録編集
  編集回数制限
  当日重複登録防止
  健康状態判定（NORMAL / WARNING / DANGER）

 工夫した点

  セッションを利用したログイン管理
   DAOパターンによるデータベースアクセス
  入力チェックによる不正データ防止
  体温・睡眠時間による健康状態の自動判定
  編集回数を制限し記録の信頼性を確保

 開発環境

  Eclipse
  Apache Tomcat 9
  H2 Database
  Windows 11
