

----------------------------------Movie

CREATE PROCEDURE CreateOrUpdateMovie
    @Title NVARCHAR(255),
    @OriginalTitle NVARCHAR(255),
    @Description NVARCHAR(MAX),
    @Duration INT,
    @Year INT,
    @Poster NVARCHAR(MAX),
    @Rating NVARCHAR(50),
    @Link NVARCHAR(MAX),
    @Guid NVARCHAR(MAX),
    @Reservation NVARCHAR(MAX),
    @DisplayDate DATETIME,
    @Performances NVARCHAR(MAX),
    @Sort INT,
    @Trailer NVARCHAR(255),
    @GenreIDs NVARCHAR(MAX),
    @PersonIDs NVARCHAR(MAX),
    @MovieID INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    -- Check if the movie already exists
    SELECT @MovieID = ID FROM Movie WHERE Title = @Title;

    IF @MovieID IS NULL
    BEGIN
        -- Movie does not exist, insert a new record
        INSERT INTO Movie (Title, OriginalTitle, Description, Duration, Year, Poster, Rating, Link, Guid, Reservation, DisplayDate, Performances, Sort, Trailer)
        VALUES (@Title, @OriginalTitle, @Description, @Duration, @Year, @Poster, @Rating, @Link, @Guid, @Reservation, @DisplayDate, @Performances, @Sort, @Trailer);

        SET @MovieID = SCOPE_IDENTITY();
    END
    ELSE
    BEGIN
        -- Movie already exists, update the record
        UPDATE Movie
        SET OriginalTitle = @OriginalTitle,
            Description = @Description,
            Duration = @Duration,
            Year = @Year,
            Poster = @Poster,
            Rating = @Rating,
            Link = @Link,
            Guid = @Guid,
            Reservation = @Reservation,
            DisplayDate = @DisplayDate,
            Performances = @Performances,
            Sort = @Sort,
            Trailer = @Trailer
        WHERE ID = @MovieID;
    END

    -- Update or insert genres
    DELETE FROM MovieGenre WHERE MovieID = @MovieID;

    IF @GenreIDs IS NOT NULL
    BEGIN
        INSERT INTO MovieGenre (MovieID, GenreID)
        SELECT @MovieID, ID
        FROM Genre
        WHERE ID IN (SELECT value FROM STRING_SPLIT(@GenreIDs, ','));
    END

    -- Update or insert persons
    DELETE FROM MoviePersonRole WHERE MovieID = @MovieID;

    IF @PersonIDs IS NOT NULL
    BEGIN
        INSERT INTO MoviePersonRole (MovieID, PersonID, RoleID)
        SELECT @MovieID, ID, 1 -- RoleID 1 for "Actor"
        FROM Person
        WHERE ID IN (SELECT value FROM STRING_SPLIT(@PersonIDs, ','));
    END

    RETURN @MovieID;
END

GO

CREATE PROCEDURE DeleteMovie
    @MovieID INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Delete movie from Movie table
    DELETE FROM Movie WHERE ID = @MovieID;

    -- Delete related records from MovieGenre and MoviePersonRole tables
    DELETE FROM MovieGenre WHERE MovieID = @MovieID;
    DELETE FROM MoviePersonRole WHERE MovieID = @MovieID;
END

GO

CREATE PROCEDURE SelectMovie
    @MovieID INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT *
    FROM Movie
    WHERE ID = @MovieID;
END


-----------------------------------genre
GO
CREATE PROCEDURE CreateOrUpdateGenre
    @Name NVARCHAR(255),
    @GenreID INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    -- Check if the genre already exists
    SELECT @GenreID = ID FROM Genre WHERE Name = @Name;

    IF @GenreID IS NULL
    BEGIN
        -- Genre does not exist, insert a new record
        INSERT INTO Genre (Name)
        VALUES (@Name);

        SET @GenreID = SCOPE_IDENTITY();
    END

    RETURN @GenreID;
END

GO

------------------------------------person
CREATE PROCEDURE CreateOrUpdatePerson
    @Name NVARCHAR(255),
    @PersonID INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    -- Check if the person already exists
    SELECT @PersonID = ID FROM Person WHERE Name = @Name;

    IF @PersonID IS NULL
    BEGIN
        -- Person does not exist, insert a new record
        INSERT INTO Person (Name)
        VALUES (@Name);

        SET @PersonID = SCOPE_IDENTITY();
    END

    RETURN @PersonID;
END


GO

------------------------------role
CREATE PROCEDURE CreateOrUpdateRole
    @Role NVARCHAR(255),
    @RoleID INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    -- Check if the role already exists
    SELECT @RoleID = ID FROM Role WHERE Role = @Role;

    IF @RoleID IS NULL
    BEGIN
        -- Role does not exist, insert a new record
        INSERT INTO Role (Role)
        VALUES (@Role);

        SET @RoleID = SCOPE_IDENTITY();
    END

    RETURN @RoleID;
END

go
---------------------------account
CREATE PROCEDURE CreateOrUpdateAccountType
    @Type NVARCHAR(255),
    @AccountTypeID INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    -- Check if the account type already exists
    SELECT @AccountTypeID = ID FROM AccountType WHERE Type = @Type;

    IF @AccountTypeID IS NULL
    BEGIN
        -- Account type does not exist, insert a new record
        INSERT INTO AccountType (Type)
        VALUES (@Type);

        SET @AccountTypeID = SCOPE_IDENTITY();
    END

    RETURN @AccountTypeID;
END


go
--------------------------user
CREATE PROCEDURE CreateOrUpdateUser
    @Username NVARCHAR(255),
    @Password NVARCHAR(255),
    @AccountTypeID INT,
    @UserID INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    -- Check if the user already exists
    SELECT @UserID = ID FROM [User] WHERE Username = @Username;

    IF @UserID IS NULL
    BEGIN
        -- User does not exist, insert a new record
        INSERT INTO [User] (Username, Password, AccountTypeID)
        VALUES (@Username, @Password, @AccountTypeID);

        SET @UserID = SCOPE_IDENTITY();
    END

    RETURN @UserID;
END

GO
CREATE PROCEDURE DeleteUser
    @UserID INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Delete user from User table
    DELETE FROM [User] WHERE ID = @UserID;

    -- Delete related records from FavoriteMovie table
    DELETE FROM FavoriteMovie WHERE UserID = @UserID;
END
GO
CREATE PROCEDURE SelectUser
    @UserID INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT *
    FROM [User]
    WHERE ID = @UserID;
END





------------------------ favorite movie
CREATE PROCEDURE CreateOrUpdateFavoriteMovie
    @UserID INT,
    @MovieID INT,
    @FavoriteMovieID INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    -- Check if the favorite movie already exists
    SELECT @FavoriteMovieID = UserID FROM FavoriteMovie WHERE UserID = @UserID AND MovieID = @MovieID;

    IF @FavoriteMovieID IS NULL
    BEGIN
        -- Favorite movie does not exist, insert a new record
        INSERT INTO FavoriteMovie (UserID, MovieID)
        VALUES (@UserID, @MovieID);

        SET @FavoriteMovieID = SCOPE_IDENTITY();
    END

    RETURN @FavoriteMovieID;
END
