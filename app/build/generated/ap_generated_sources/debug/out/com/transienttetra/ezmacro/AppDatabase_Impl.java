package com.transienttetra.ezmacro;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile FoodItemDao _foodItemDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `FoodItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `description` TEXT, `barcode` TEXT, `servings` REAL NOT NULL, `weight` REAL NOT NULL, `isFavorite` INTEGER NOT NULL, `energy` REAL, `protein` REAL, `fats` REAL, `carbohydrates` REAL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd6c0498eea36c36c89929239300e67b0')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `FoodItem`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFoodItem = new HashMap<String, TableInfo.Column>(11);
        _columnsFoodItem.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("barcode", new TableInfo.Column("barcode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("servings", new TableInfo.Column("servings", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("energy", new TableInfo.Column("energy", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("protein", new TableInfo.Column("protein", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("fats", new TableInfo.Column("fats", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoodItem.put("carbohydrates", new TableInfo.Column("carbohydrates", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFoodItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFoodItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFoodItem = new TableInfo("FoodItem", _columnsFoodItem, _foreignKeysFoodItem, _indicesFoodItem);
        final TableInfo _existingFoodItem = TableInfo.read(_db, "FoodItem");
        if (! _infoFoodItem.equals(_existingFoodItem)) {
          return new RoomOpenHelper.ValidationResult(false, "FoodItem(com.transienttetra.ezmacro.FoodItem).\n"
                  + " Expected:\n" + _infoFoodItem + "\n"
                  + " Found:\n" + _existingFoodItem);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d6c0498eea36c36c89929239300e67b0", "917bc1adba40faf756b3ce09f73d24de");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "FoodItem");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `FoodItem`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(FoodItemDao.class, FoodItemDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public FoodItemDao FoodItemDao() {
    if (_foodItemDao != null) {
      return _foodItemDao;
    } else {
      synchronized(this) {
        if(_foodItemDao == null) {
          _foodItemDao = new FoodItemDao_Impl(this);
        }
        return _foodItemDao;
      }
    }
  }
}
